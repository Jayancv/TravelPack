package org.jcv.search.service;

import org.jcv.common.result.dto.BaseResultDto;
import org.jcv.search.model.criteria.BaseSearchCriteria;

import java.util.List;

public interface ISearchService<T extends BaseSearchCriteria, U extends BaseResultDto> {
    List<U> summarySearch(T criteria);
    U detailSearch(T criteria);
}
