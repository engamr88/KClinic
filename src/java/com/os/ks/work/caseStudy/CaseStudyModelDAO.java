/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.caseStudy;

import com.os.ks.work.caseStudy.*;
import com.os.models.CaseStudy;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class CaseStudyModelDAO extends AbstractDAOWrapper<CaseStudyModelWrapper> {

    public CaseStudyModelDAO() {
        initModelWrapper();
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + CaseStudyModelWrapper.class.getName() + "("
                + " (model.caseStudyId) as id , "
                + " (model.caseStudyName) as caseStudyName "
                + ") "
                + " FROM CaseStudy model";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.caseStudyId) "
                + " FROM CaseStudy model ";
        //  + " where model.caseStudyType in (1,4) group by model.caseStudyId";
//        extraCondition = " model.caseStudyType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM CaseStudy model where model.caseStudyId= " + id;
        return HQL;
    }

    public CaseStudy loadCaseStudyById(Integer id) {
        String HQL = "FROM CaseStudy model where model.caseStudyId= " + id;
        return (CaseStudy) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        CaseStudy d = (CaseStudy) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public CaseStudyModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        CaseStudyModelWrapper caseStudyMW = new CaseStudyModelWrapper();
        setModelWrapper(caseStudyMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
