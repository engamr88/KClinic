/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.category;

import com.os.ks.work.category.*;
import com.os.ks.work.category.*;
import com.os.models.Category;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class CategoryModelDAO extends AbstractDAOWrapper<CategoryModelWrapper> {

    public CategoryModelDAO() {
        initModelWrapper();
        elementsMap.put("id", "model.categoryId");
        elementsMap.put("categoryTitle", "model.categoryTitle");
    }

    public List<CategoryModelWrapper> loadCategoryWrapperList() {
        String HQL = "SELECT "
                + " new " + CategoryModelWrapper.class.getName() + "("
                + " (model.categoryId) as id , "
                + " (model.categoryTitle) as categoryTitle "
                + ") "
                + " FROM Category model";
        return list(HQL);
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + CategoryModelWrapper.class.getName() + "("
                + " (model.categoryId) as id , "
                + " (model.categoryTitle) as categoryTitle "
                + ") "
                + " FROM Category model";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.categoryId) "
                + " FROM Category model ";
        //  + " where model.categoryType in (1,4) group by model.categoryId";
//        extraCondition = " model.categoryType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Category model where model.categoryId= " + id;
        return HQL;
    }

    public Category loadCategoryById(Integer id) {
        String HQL = "FROM Category model where model.categoryId= " + id;
        return (Category) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Category d = (Category) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public CategoryModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        CategoryModelWrapper categoryMW = new CategoryModelWrapper();
        setModelWrapper(categoryMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
