/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.priceList;

import com.os.models.PriceList;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class PriceListModelDAO extends AbstractDAOWrapper<PriceListModelWrapper> {

    public PriceListModelDAO() {
        initModelWrapper();
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {
//Integer id, String categoryTitle, String priceType, Double priceAmount
        String HQL = "SELECT "
                + " new " + PriceListModelWrapper.class.getName() + "("
                + " (model.priceListId) as id , "
                + " (category.categoryTitle) as categoryTitle, "
                + " (model.priceType) as priceType, "
                + " (model.priceAmount) as priceAmount "
                + ") "
                + " FROM PriceList model left join model.category category";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    public List<PriceList> loadPriceListByCategoryId(int categoryId) {
        String hql = "FROM PriceList model left join fetch model.category category where category.categoryId=" + categoryId;
        return list(hql);
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.priceListId) "
                + " FROM PriceList model ";
        //  + " where model.priceListType in (1,4) group by model.priceListId";
//        extraCondition = " model.priceListType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM PriceList model left join fetch model.category category where model.priceListId= " + id;
        return HQL;
    }

    public PriceList loadPriceListById(Integer id) {
        String HQL = "FROM PriceList model left join fetch model.category category where model.priceListId= " + id;
        return (PriceList) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        PriceList d = (PriceList) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public PriceListModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        PriceListModelWrapper priceListMW = new PriceListModelWrapper();
        setModelWrapper(priceListMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
