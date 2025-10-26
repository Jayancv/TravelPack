package org.jcv.search.service;

import org.jcv.search.model.criteria.BaseSearchCriteria;
import org.jcv.search.model.result.Alternative;
import org.jcv.search.model.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkupCalculator {
    private static final Logger logger = LoggerFactory.getLogger("MarkupCalculator");

    private double markupRate = 110;

    public void calculateMarkup(BaseResult result, BaseSearchCriteria criteria) {

        int adult = criteria.getAdult();
        int teen = 0;
        int child = criteria.getChild();
        int inf = 0;

        for (Alternative alt : result.getAlternatives()) {
            double adultCost = alt.getAdultCost() * adult;
            double teenCost = alt.getTeenCost() * teen;
            double childCost = alt.getChildCost() * child;
            double infantCost = alt.getInfantCost() * inf;

            double adultPrice = (adultCost * markupRate) / 100;
            double teenPrice = (teenCost * markupRate) / 100;
            double childPrice = (childCost * markupRate) / 100;
            double infantPrice = (infantCost * markupRate) / 100;

            double totalCost = adultCost + teenCost + childCost + infantCost;
            double totalPrice = adultPrice + teenPrice + childPrice + infantPrice;

            alt.setCost(totalCost);
            alt.setPrice(totalPrice);

            alt.setAdultPrice((alt.getAdultCost() * markupRate) / 100);
            alt.setTeenPrice((alt.getTeenCost() * markupRate) / 100);
            alt.setChildPrice((alt.getChildCost() * markupRate) / 100);
            alt.setInfantPrice((alt.getInfantCost() * markupRate) / 100);

            logger.info("Markup calculated, rate:{}, totalCost:{}, totalPrice:{}", markupRate, totalCost, totalPrice);
        }

        result.setSelectedAlternative(result.getAlternatives().get(0));

    }
}
