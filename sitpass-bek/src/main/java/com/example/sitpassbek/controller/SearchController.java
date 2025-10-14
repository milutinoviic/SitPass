package com.example.sitpassbek.controller;


import com.example.sitpassbek.dto.index.SearchQueryDTO;
import com.example.sitpassbek.indexmodel.FacilityIndex;
import com.example.sitpassbek.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/simple")
    public Page<FacilityIndex> simpleSearch(
            @RequestBody SearchQueryDTO simpleSearchQuery,
            Pageable pageable) {
        return searchService.simpleSearch(simpleSearchQuery.keywords(), simpleSearchQuery.ranges(), simpleSearchQuery.isAsc(), pageable);
    }

    @PostMapping("/advance")
    public Page<FacilityIndex> advancedSearch(@RequestBody SearchQueryDTO advancedSearchQuery,
                                              Pageable pageable) {
        return searchService.advancedSearch(advancedSearchQuery.expression(), advancedSearchQuery.ranges(),advancedSearchQuery.isAsc() ,pageable);
    }

    @PostMapping("/mlt")
    public Page<FacilityIndex> MLTSearch(@RequestBody SearchQueryDTO mltQuery, Pageable pageable) {
        return searchService.MLTSearch(mltQuery.keywords().get(0), mltQuery.isAsc(), pageable);
    }



}
