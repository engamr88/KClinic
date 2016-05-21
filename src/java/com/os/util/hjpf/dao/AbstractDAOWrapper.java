/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util.hjpf.dao;

import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mohamed
 */
public abstract class AbstractDAOWrapper<T extends AbstractModelWrapper> extends AbstractDAO implements Serializable {

    protected T modelWrapper;
    protected Map generalListFilters = new HashMap();
    protected String extraCondition = null;
    protected Map elementsMap = new HashMap();

    protected String createSearchCrti(String orderBy, String orderMode, Map filters) {
        String HQL = "";
        filters.putAll(generalListFilters);

        if (filters.size() > 0 || extraCondition != null) {
            if (extraCondition != null) {
                HQL += " Where " + extraCondition;
            } else {
                HQL += " Where 1=1 ";
            }
        }

        String condition;
        for (Iterator it = filters.keySet().iterator(); it.hasNext();) {
            Object key = it.next();
            if (!elementsMap.isEmpty()) {
                condition = elementsMap.get(key) + " like '%" + filters.get(key) + "%'";
            } else {
                condition = key + " like '%" + filters.get(key) + "%'";
            }

            HQL += " and " + condition;
        }

        if (orderBy != null) {
            if (!elementsMap.isEmpty()) {
                HQL += " ORDER BY  " + elementsMap.get(orderBy);
            } else {
                HQL += " ORDER BY  " + orderBy;
            }

            if (orderMode != null) {
                HQL += " " + orderMode;
            }
        }
        return HQL;
    }

    /* protected boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String modelClass) {
     String HQL = "SELECT (model." + idName + ") as id FROM " + modelClass + " model "
     + " WHERE  id!=" + idvalue + " and model." + fieldName + " like '" + fieldValue.trim() + "'";
     List l = list(HQL);
     return (l == null || l.isEmpty());
     }*/
    protected boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        String HQL = "SELECT (model." + idName + ") as id FROM " + modelClass + " model "
                + " WHERE  id<>" + idvalue+" and model.userType =2";
        if (fieldName != null && fieldValue != null) {
            HQL += " and model." + fieldName + " like '" + fieldValue.trim() + "'";
        }
        if (extraCondition != null && !extraCondition.trim().isEmpty()) {
            HQL += " and " + extraCondition;
        }
        List l = list(HQL);
        return (l == null || l.isEmpty());
    }

    public void setModelWrapper(T modelWrapper) {
        this.modelWrapper = modelWrapper;
    }

    public T getModelWrapper() {
        return modelWrapper;
    }

    /*  @Override
     public List list(int first, int pageSize, String orderBy, String orderMode, Map filters) {
      
     return super.list(first, pageSize, orderBy, orderMode, filters);
     }*/

    /*  @Override
     public Long fetchCount(String orderBy, String orderMode, Map filters) {
     return super.fetchCount(orderBy, orderMode, filters);
     }*/
    public boolean save() {

        return super.save(modelWrapper.getModel());
    }

    public boolean saveList(ArrayList<T> modelWrabbers) {
        List<Object> models = new ArrayList<>();
        for (T wrraper : modelWrabbers) {
            models.add(wrraper.getModel());
        }
        return super.save(models);
    }

    public Object saveMerge() {
        return super.saveMerge(modelWrapper.getModel());
    }

    public boolean saveOrUpdate() {
        return super.saveOrUpdate(modelWrapper.getModel());
    }

    public boolean delete() {
        return super.delete(modelWrapper.getModel());
    }

    public boolean update() {
        return super.update(modelWrapper.getModel());
    }

    public abstract void clear();

    public abstract void loadModelWrapper();
}
