package org.jcv.search.specification;

import jakarta.persistence.criteria.Path;
import org.jcv.search.model.criteria.TourSearchCriteria;
import org.jcv.search.model.data.TourContract;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;
import java.time.LocalDate;

public class TourSpecification {
    public static Specification<TourContract> fromCriteria(TourSearchCriteria criteria) {
        return Specification
                .where(destinationEquals(criteria.getDestinationCity()))
                .and(validDateBetween(criteria.getFromDate(), criteria.getToDate()))
                .and(durationBetween(criteria.getDuration(), criteria.getDuration()))
//                .and(priceLessThan(criteria.getMaxPrice()))
                .and(salesEnabled(true));
    }

    private static Specification<TourContract> destinationEquals(String destination) {
        return (root, query, cb) ->
                destination == null ? null :
                        cb.equal(root.get("cityCode"), destination);
    }

    private static Specification<TourContract> validDateBetween(LocalDate localDateFrom, LocalDate localDateTo) {
        Date from = (localDateFrom != null) ? Date.valueOf(localDateFrom) : null;
        Date to = (localDateTo != null) ? Date.valueOf(localDateTo) : null;

        return (root, query, cb) -> {
            if (from == null && to == null) return null;

            Path<Date> validFromPath = root.get("validFrom");
            Path<Date> validToPath = root.get("validTo");

            if (from != null && to != null) {
                return cb.and(
                        cb.lessThanOrEqualTo(validFromPath, from),
                        cb.greaterThanOrEqualTo(validToPath, to)
                );
            } else if (from != null) {
                return cb.lessThanOrEqualTo(validFromPath, from);
            } else { // to != null
                return cb.greaterThanOrEqualTo(validToPath, to);
            }
        };
    }

    private static Specification<TourContract> durationBetween(Integer min, Integer max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min != null && max != null)
                return cb.between(root.get("duration"), min, max);
            if (min != null)
                return cb.greaterThanOrEqualTo(root.get("duration"), min);
            return cb.lessThanOrEqualTo(root.get("duration"), max);
        };
    }

    private static Specification<TourContract> priceLessThan(Double maxPrice) {
        return (root, query, cb) ->
                maxPrice == null ? null :
                        cb.lessThanOrEqualTo(root.get("basePrice"), maxPrice);
    }

    private static Specification<TourContract> salesEnabled(Boolean enabled) {
        return (root, query, cb) ->
                enabled == null ? null :
                        cb.equal(root.get("salesEnabled"), enabled);
    }

}
