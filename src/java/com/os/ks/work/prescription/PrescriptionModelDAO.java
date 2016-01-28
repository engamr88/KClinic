/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.prescription;

import com.os.models.Concentration;
import com.os.models.Detection;
import com.os.models.Dose;
import com.os.models.Form;
import com.os.models.Medication;
import com.os.models.Patient;
import com.os.models.Prescription;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class PrescriptionModelDAO extends AbstractDAOWrapper<PrescriptionModelWrapper> {

    public PrescriptionModelDAO() {
        initModelWrapper();
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {
//(Integer id, String doseName, String medicationName, String concentrationName, String formName, String patientFullName, Date prescriptionDate) {
        String HQL = "SELECT "
                + " new " + PrescriptionModelWrapper.class.getName() + "("
                + " (model.prescriptionId) as id , "
                + " (dose.doseName) as doseName, "
                + " (medication.medicationName) as medicationName, "
                + " (concentration.concentrationName) as concentrationName, "
                + " (form.formName) as formName, "
                + " (patient.patientFullName) as patientFullName, "
                + " (model.prescriptionDate) as prescriptionDate "
                + ") "
                + " FROM Prescription model"
                + " left join fetch model.dose dose"
                + " left join fetch model.patient patient"
                + " left join fetch model.form form"
                + " left join fetch model.concentration concentration"
                + " left join fetch model.medication medication";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.prescriptionId) "
                + " FROM Prescription model ";
        //  + " where model.prescriptionType in (1,4) group by model.prescriptionId";
//        extraCondition = " model.prescriptionType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Prescription model"
                 + " left join fetch model.dose dose"
                + " left join fetch model.patient patient"
                + " left join fetch model.form form"
                + " left join fetch model.concentration concentration"
                + " left join fetch model.medication medication"
                + " where model.prescriptionId= " + id;
        return HQL;
    }

    public Prescription loadPrescriptionById(Integer id) {
        String HQL = "FROM Prescription model"
                + " left join fetch model.dose dose"
                + " left join fetch model.patient patient"
                + " left join fetch model.form form"
                + " left join fetch model.concentration concentration"
                + " left join fetch model.medication medication"
                + " where model.prescriptionId= " + id;
        return (Prescription) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Prescription d = (Prescription) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public PrescriptionModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        PrescriptionModelWrapper prescriptionMW = new PrescriptionModelWrapper();
        prescriptionMW.setModel(new Prescription());
        prescriptionMW.getModel().setMedication(new Medication());
        prescriptionMW.getModel().setDose(new Dose());
        prescriptionMW.getModel().setConcentration(new Concentration());
        prescriptionMW.getModel().setDetection(new Detection());
        prescriptionMW.getModel().setForm(new Form());
        setModelWrapper(prescriptionMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
