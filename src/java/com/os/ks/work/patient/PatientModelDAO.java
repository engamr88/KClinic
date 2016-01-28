/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.patient;

import com.os.models.Patient;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class PatientModelDAO extends AbstractDAOWrapper<PatientModelWrapper> {

    public PatientModelDAO() {
        initModelWrapper();
    }

    public List<PatientModelWrapper> loadPatientWrapperList() {
        String HQL = "SELECT "
                + " new " + PatientModelWrapper.class.getName() + "("
                + " (model.patientId) as id , "
                + " (model.patientFullName) as patientFullName, "
                + " (model.patientMobile) as patientMobile, "
                + " (model.patientAddress) as patientAddress "
                + ") "
                + " FROM Patient model";
        return list(HQL);
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + PatientModelWrapper.class.getName() + "("
                + " (model.patientId) as id , "
                + " (model.patientFullName) as patientFullName, "
                + " (model.patientMobile) as patientMobile, "
                + " (model.patientAddress) as patientAddress "
                + ") "
                + " FROM Patient model";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.patientId) "
                + " FROM Patient model ";
        //  + " where model.patientType in (1,4) group by model.patientId";
//        extraCondition = " model.patientType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Patient model where model.patientId= " + id;
        return HQL;
    }

    public Patient loadPatientById(Integer id) {
        String HQL = "FROM Patient model where model.patientId= " + id;
        return (Patient) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Patient d = (Patient) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public PatientModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        PatientModelWrapper patientMW = new PatientModelWrapper();
        setModelWrapper(patientMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
