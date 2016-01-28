/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.priceList;

import com.os.ks.work.priceList.*;
import com.os.ks.work.priceList.*;
import com.os.models.Category;
import com.os.models.PriceList;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class PriceListModelWrapper extends AbstractModelWrapper<PriceList> {

    public PriceListModelWrapper() {
        initModel();
    }
    private Integer id;
    private String categoryTitle;
    private String priceType;
    private Double priceAmount;

    public PriceListModelWrapper(Integer id, String categoryTitle, String priceType, Double priceAmount) {
        this.id = id;
        this.categoryTitle = categoryTitle;
        this.priceType = priceType;
        this.priceAmount = priceAmount;
    }

    

    @Override
    public PriceList getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        PriceList priceList = new PriceList();
        priceList.setCategory(new Category());
        setModel(priceList);
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Double getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Double priceAmount) {
        this.priceAmount = priceAmount;
    }

  
}
