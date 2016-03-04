/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.reservation;

/**
 *
 * @author amr
 */
public class PriceWrapper {
    private Integer priceListId;
    private String priceName;
    private Double priceAmount;
    private Integer selected;

    public PriceWrapper() {
    }

    public PriceWrapper(Integer priceListId, String priceName, Double priceAmount, Integer selected) {
        this.priceListId = priceListId;
        this.priceName = priceName;
        this.priceAmount = priceAmount;
        this.selected = selected;
    }

    public Integer getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public Double getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Double priceAmount) {
        this.priceAmount = priceAmount;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
    
}
