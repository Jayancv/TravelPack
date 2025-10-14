package org.jcv.search.model.result;

import lombok.Data;

@Data
public class CostPrice {
    private double price;
    private double cost;

    private double adultCost;
    private double teenCost;
    private double childCost;
    private double infantCost;

    private double adultPrice;
    private double teenPrice;
    private double childPrice;
    private double infantPrice;


    public void add(CostPrice cp){
        price +=cp.getPrice();
        cost +=cp.getCost();

        adultCost += cp.getAdultCost();
        teenCost += cp.getTeenCost();
        childCost += cp.getChildCost();
        infantCost += cp.getInfantCost();

        adultPrice += cp.getAdultPrice();
        teenPrice += cp.getTeenPrice();
        childPrice += cp.getChildPrice();
        infantPrice += cp.getInfantPrice();


    }
}
