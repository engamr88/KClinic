/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.detectionStudy;


import com.os.models.CaseStudy;
import com.os.models.Detection;
import com.os.models.DetectionStudy;
import com.os.models.Patient;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class DetectionStudyModelDAO extends AbstractDAOWrapper<DetectionStudyModelWrapper> {

    public DetectionStudyModelDAO() {
        initModelWrapper();
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + DetectionStudyModelWrapper.class.getName() + "("
                + " (model.detectionStudyId) as id , "
                + " (model.caseStudy) as caseStudy "
                + ") "
                + " FROM DetectionStudy model left join model.caseStudy";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.detectionStudyId) "
                + " FROM DetectionStudy model ";
        //  + " where model.detectionStudyType in (1,4) group by model.detectionStudyId";
//        extraCondition = " model.detectionStudyType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM DetectionStudy model left join fetch model.caseStudy caseStudy where model.detectionStudyId= " + id;
        return HQL;
    }

    public DetectionStudy loadDetectionStudyById(Integer id) {
        String HQL = "FROM DetectionStudy model left join fetch model.caseStudy caseStudy where model.detectionStudyId= " + id;
        return (DetectionStudy) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        DetectionStudy d = (DetectionStudy) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public DetectionStudyModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        DetectionStudyModelWrapper detectionStudyMW = new DetectionStudyModelWrapper();
        detectionStudyMW.setModel(new DetectionStudy());
        detectionStudyMW.getModel().setCaseStudy(new CaseStudy());
        detectionStudyMW.getModel().setDetection(new Detection());
        detectionStudyMW.getModel().getDetection().setPatient(new Patient());
        setModelWrapper(detectionStudyMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
