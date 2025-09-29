package com.example.sitpassbek.indexService.Impl;

import com.example.sitpassbek.indexModel.FacilityIndex;
import com.example.sitpassbek.indexService.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final ElasticsearchOperations elasticsearchTemplate;

    @Override
    public Page<FacilityIndex> searchFacilities(String keyword, Pageable pageable) {
        NativeQuery searchQuery = new NativeQueryBuilder()
                .withQuery(q -> q
                        .multiMatch(m -> m
                                .query(keyword)
                                .fields("name",
                                        "descriptionSrp",
                                        "descriptionEng",
                                        "fileDescriptionSrp",
                                        "fileDescriptionEng")
                        )
                )
                .withPageable(pageable)
                .build();

        var searchHits = elasticsearchTemplate.search(searchQuery, FacilityIndex.class);
        var searchHitsPaged = SearchHitSupport.searchPageFor(searchHits, pageable);

        List<FacilityIndex> content = searchHitsPaged.getContent().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        return new PageImpl<>(
                content,
                searchHitsPaged.getPageable(),
                searchHitsPaged.getTotalElements()
        );
    }
}
