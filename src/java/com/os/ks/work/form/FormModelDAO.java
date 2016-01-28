/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.form;

import com.os.ks.work.form.*;
import com.os.ks.work.form.*;
import com.os.ks.work.form.*;
import com.os.models.Form;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class FormModelDAO extends AbstractDAOWrapper<FormModelWrapper> {

    public FormModelDAO() {
        initModelWrapper();
    }

    public List<Form> loadFormList() {
        String hql = " FROM Form model";
        return list(hql);
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + FormModelWrapper.class.getName() + "("
                + " (model.formId) as id , "
                + " (model.formName) as formName "
                + ") "
                + " FROM Form model";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.formId) "
                + " FROM Form model ";
        //  + " where model.formType in (1,4) group by model.formId";
//        extraCondition = " model.formType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Form model where model.formId= " + id;
        return HQL;
    }

    public Form loadFormById(Integer id) {
        String HQL = "FROM Form model where model.formId= " + id;
        return (Form) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Form d = (Form) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public FormModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        FormModelWrapper formMW = new FormModelWrapper();
        setModelWrapper(formMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
