/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.detection;

import com.os.models.Detection;
import com.os.models.User;
import com.os.models.Patient;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class DetectionModelDAO extends AbstractDAOWrapper<DetectionModelWrapper> {

    public DetectionModelDAO() {
        initModelWrapper();
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + DetectionModelWrapper.class.getName() + "("
                + " (model.detectionId) as id , "
                + " (patient.patientFullName) as patientFullName,"
                + " (model.note) as note,"
                + " (doctor.userFullName) as doctorFullName,"
                + " (model.extraFees) as extraFees "
                + ") "
                + " FROM Detection model left join fetch model.patient patient left join model.user doctor";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.detectionId) "
                + " FROM Detection model ";
        //  + " where model.detectionType in (1,4) group by model.detectionId";
//        extraCondition = " model.detectionType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Detection model left join fetch model.patient patient left join fetch model.user doctor where model.detectionId= " + id;
        return HQL;
    }

    public Detection loadDetectionById(Integer id) {
        String HQL = "FROM Detection model left join fetch model.patient patient left join fetch model.user doctor where model.detectionId= " + id;
        return (Detection) uniqueResult(HQL);
    }

    public Detection checkDetectionCreatedFromReservation(Integer reservationId) {
        String HQL = "FROM Detection model where model.archive =0 and model.patient.patientId = "
                + "(select patient.patientId from "
                + "Reservation reserv left join  reserv.patient patient "
                + "where reserv.reservationId=" + reservationId + ")";
        return (Detection) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Detection d = (Detection) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public DetectionModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        DetectionModelWrapper detectionMW = new DetectionModelWrapper();
        detectionMW.setModel(new Detection());
        detectionMW.getModel().setDoctor(new User());
        detectionMW.getModel().setPatient(new Patient());
        setModelWrapper(detectionMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
