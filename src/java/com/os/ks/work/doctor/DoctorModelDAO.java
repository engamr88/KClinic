/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.doctor;

import com.os.ks.work.doctor.*;
import com.os.models.Doctor;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class DoctorModelDAO extends AbstractDAOWrapper<DoctorModelWrapper> {

    public DoctorModelDAO() {
        initModelWrapper();
    }

    public DoctorModelWrapper fetchLoginedDoctor(String doctorUserName, String enctyptpassword) {
        String HQL = "SELECT "
                + " new " + DoctorModelWrapper.class.getName() + "("
                + " (model.doctorId) as id , "
                + " (model.doctorUserName) as doctorUserName,"
                + " (model.doctorPassword) as doctorPassword "
                + ") "
                + " from Doctor model where model.doctorUserName = '" + doctorUserName + "' and model.doctorPassword = '" + enctyptpassword + "' ";
        HQL += createSearchCrti(null, null, new HashMap());
        return (DoctorModelWrapper) uniqueResult(HQL);
    }

   
    public Doctor fetchLoginedDoctor(Integer doctorId) {
        String HQL = " from Doctor model where model.doctorId = " + doctorId;

        return (Doctor) uniqueResult(HQL);
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {
     
        String HQL = "SELECT "
                + " new " + DoctorModelWrapper.class.getName() + "("
                + " (model.doctorId) as id , "
                + " (model.doctorFullName) as doctorFullName, "
                + " (model.doctorMobile) as doctorMobile, "
                + " (model.doctorAddress) as doctorAddress "
                + ") "
                + " FROM Doctor model";

        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.doctorId) "
                + " FROM Doctor model ";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Doctor model where model.doctorId= " + id;
        return HQL;
    }

    public Doctor loadDoctorById(Integer id) {
        String HQL = "FROM Doctor model where model.doctorId= " + id;
        return (Doctor) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Doctor d = (Doctor) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public DoctorModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        DoctorModelWrapper doctorMW = new DoctorModelWrapper();
        setModelWrapper(doctorMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
