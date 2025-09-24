package com.example.sitpassbek.indexService.Impl;

import com.example.sitpassbek.indexDto.FacilitySearchDTO;
import com.example.sitpassbek.indexService.FacilitySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.example.sitpassbek.indexModel.FacilityIndex;
import co.elastic.clients.json.JsonData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;


@Service
@RequiredArgsConstructor
@Slf4j
public class FacilitySearchServiceImpl implements FacilitySearchService {


    private final ElasticsearchOperations elasticsearchTemplate;

    private static final String INDEX = "facility_index";

    @Override
    public Page<FacilityIndex> search(FacilitySearchDTO dto, Pageable pageable) {
        // Basic null checks and defaults
        var keywords = dto.keywords() == null ? List.of() : dto.keywords();
        var operator = dto.booleanOperator() == null ? "AND" : dto.booleanOperator().toUpperCase();

        // If moreLikeThis flag set -> build MLT query
        NativeQuery searchQuery;
        if (Boolean.TRUE.equals(dto.moreLikeThis()) && !keywords.isEmpty()) {
            searchQuery = new NativeQueryBuilder()
                    .withQuery(buildMoreLikeThisQuery(Strings.join(keywords, " ")))
                    .withPageable(pageable)
                    .build();
        } else {
            searchQuery = new NativeQueryBuilder()
                    .withQuery(buildCombinedQuery(keywords, operator, dto))
                    .withPageable(pageable)
                    .withSort(s -> {
                        var sortBy = dto.sortBy() == null ? "name" : dto.sortBy();
                        var dir = dto.sortDirection() == null ? "ASC" : dto.sortDirection().toUpperCase();
                        if ("DESC".equals(dir)) {
                            s.field(f -> f.field(sortBy).order(co.elastic.clients.elasticsearch._types.SortOrder.Desc));
                        } else {
                            s.field(f -> f.field(sortBy).order(co.elastic.clients.elasticsearch._types.SortOrder.Asc));
                        }
                        return s;
                    })
                    .build();
        }

        var searchHits = elasticsearchTemplate.search(searchQuery, FacilityIndex.class, IndexCoordinates.of(INDEX));
        var searchHitsPaged = SearchHitSupport.searchPageFor(searchHits, searchQuery.getPageable());
        return (Page<FacilityIndex>) SearchHitSupport.unwrapSearchHits(searchHitsPaged);
    }

    private Query buildMoreLikeThisQuery(String likeText) {
        // MoreLikeThisQuery: fields = title, description, pdf content
        return Query.of(q -> q.moreLikeThis(mlt -> mlt
                .fields("name", "descriptionSrp", "descriptionEng", "fileDescription_srp", "fileDescription_eng")
                .like(l -> l.text(likeText))
                .minTermFreq(1)   // empirically chosen; tune if needed
                .maxQueryTerms(25)
                .minDocFreq(1)
        ));
    }

    private Query buildCombinedQuery(List<String> keywords, String operator, FacilitySearchDTO dto) {
        // Build a bool query that:
        // - For each keyword generates a multi-field match (name, description*, fileDescription*)
        // - Supports phrase (quotes), prefix (*), fuzzy (~prefix) tokens
        // - Applies numeric range filters for reviewCount and avg* fields
        List<Query> tokenQueries = new ArrayList<>();

        for (String raw : keywords) {
            var tokenQuery = buildTokenQuery(raw);
            if (tokenQuery != null) tokenQueries.add(tokenQuery);
        }

        return Query.of(q -> q.bool(b -> {
            // Combine token-level queries with AND/OR depending on operator
            if (tokenQueries.isEmpty()) {
                // match_all if no keywords
            } else if ("OR".equals(operator)) {
                b.should(sb -> sb.bool(inner -> {
                    tokenQueries.forEach(tq -> inner.must(m -> m.query(tq)));
                    return inner;
                }));
            } else {
                // AND
                tokenQueries.forEach(tq -> b.must(mb -> mb.query(tq)));
            }

            // Add numeric/range filters as must clauses
            // reviewCount
            if (dto.reviewCountFrom() != null || dto.reviewCountTo() != null) {
                b.filter(f -> f.range(r -> {
                    r.field("reviewCount");
                    if (dto.reviewCountFrom() != null) r.gte(co.elastic.clients.elasticsearch._types.FieldValue.of(dto.reviewCountFrom()));
                    if (dto.reviewCountTo() != null) r.lte(co.elastic.clients.elasticsearch._types.FieldValue.of(dto.reviewCountTo()));
                    return r;
                }));
            }

            // avgEquipmentGrade
            applyDoubleRangeFilter(b, "avgEquipmentGrade", dto.avgEquipmentFrom(), dto.avgEquipmentTo());
            applyDoubleRangeFilter(b, "avgStaffGrade", dto.avgStaffFrom(), dto.avgStaffTo());
            applyDoubleRangeFilter(b, "avgHygieneGrade", dto.avgHygieneFrom(), dto.avgHygieneTo());
            applyDoubleRangeFilter(b, "avgSpaceGrade", dto.avgSpaceFrom(), dto.avgSpaceTo());

            return b;
        }));
    }

    private void applyDoubleRangeFilter(BoolQuery.Builder b, String field, Double from, Double to) {
        if (from != null || to != null) {
            b.filter(f -> f.range(r -> {
                r.field(field);
                if (from != null) r.gte(co.elastic.clients.elasticsearch._types.FieldValue.of(from));
                if (to != null) r.lte(co.elastic.clients.elasticsearch._types.FieldValue.of(to));
                return r;
            }));
        }
    }

    private Query buildTokenQuery(String raw) {
        raw = raw.trim();
        // phrase query (enclosed in double quotes)
        if (raw.startsWith("\"") && raw.endsWith("\"") && raw.length() > 1) {
            var phrase = raw.substring(1, raw.length()-1);
            return Query.of(q -> q.matchPhrase(mp -> mp.field("name").query(phrase)));
        }

        // prefix query (ends with '*')
        if (raw.endsWith("*") && raw.length() > 1) {
            var prefix = raw.substring(0, raw.length()-1);
            return Query.of(q -> q.prefix(p -> p.field("name").value(prefix)));
        }

        // fuzzy query (starts with '~')
        if (raw.startsWith("~") && raw.length() > 1) {
            var fuzzy = raw.substring(1);
            // use match with fuzziness
            return Query.of(q -> q.match(m -> m.field("name").fuzziness("AUTO").query(fuzzy)));
        }

        // default: multi-match on multiple fields with boosting for name
        return Query.of(q -> q.bool(b -> b.should(s -> s.match(m -> m.field("name").query(raw).boost(3.0f)))
                .should(s -> s.match(m -> m.field("descriptionSrp").query(raw)))
                .should(s -> s.match(m -> m.field("descriptionEng").query(raw)))
                .should(s -> s.match(m -> m.field("fileDescription_srp").query(raw).boost(0.8f)))
                .should(s -> s.match(m -> m.field("fileDescription_eng").query(raw).boost(0.8f)))
        ));
    }

}
