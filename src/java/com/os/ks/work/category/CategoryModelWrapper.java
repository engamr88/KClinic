/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.category;

import com.os.ks.work.category.*;
import com.os.models.Category;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class CategoryModelWrapper extends AbstractModelWrapper<Category> {

    public CategoryModelWrapper() {
        initModel();
    }
    private Integer id;
    private String categoryTitle;

    public CategoryModelWrapper(Integer id, String categoryTitle) {
        this.id = id;
        this.categoryTitle = categoryTitle;
    }

    @Override
    public Category getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Category category = new Category();
        setModel(new Category());
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

   
}
