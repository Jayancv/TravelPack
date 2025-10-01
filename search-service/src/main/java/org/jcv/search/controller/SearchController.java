package org.jcv.search.controller;

import org.jcv.search.dto.Result;
import org.jcv.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/search-service")
public class SearchController
{
    @Autowired
    private SearchService searchService;

    @GetMapping("/products")
    public List<Result> getProducts( @RequestParam(required = false) List<Integer> productIds,
                                     @RequestParam(required = false) List<String> productCodes,
                                     @RequestParam(required = false) String productName )
    {
        return searchService.searchProducts( productIds, productCodes, productName );
    }

}
