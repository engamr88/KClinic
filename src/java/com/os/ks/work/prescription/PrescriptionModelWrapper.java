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
import com.os.models.Prescription;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.util.Date;

/**
 *
 * @author egyptianeagle
 */
public class PrescriptionModelWrapper extends AbstractModelWrapper<Prescription> {

    public PrescriptionModelWrapper() {
        initModel();
    }
    private Integer id;
    private String doseName;
    private String medicationName;
    private String concentrationName;
    private String formName;
    private String patientFullName;
    private Date prescriptionDate;

    public PrescriptionModelWrapper(Integer id, String doseName, String medicationName, String patientFullName, Date prescriptionDate) {
        this.id = id;
        this.doseName = doseName;
        this.medicationName = medicationName;
        this.patientFullName = patientFullName;
        this.prescriptionDate = prescriptionDate;
    }

    public PrescriptionModelWrapper(Integer id, String doseName, String medicationName, String concentrationName, String formName, String patientFullName, Date prescriptionDate) {
        this.id = id;
        this.doseName = doseName;
        this.medicationName = medicationName;
        this.concentrationName = concentrationName;
        this.formName = formName;
        this.patientFullName = patientFullName;
        this.prescriptionDate = prescriptionDate;
    }

    @Override
    public Prescription getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Prescription prescription = new Prescription();
        prescription.setDose(new Dose());
        prescription.setMedication(new Medication());
        prescription.setConcentration(new Concentration());
        prescription.setForm(new Form());
        prescription.setDetection(new Detection());
        setModel(prescription);
    }

    public String getDoseName() {
        return doseName;
    }

    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getConcentrationName() {
        return concentrationName;
    }

    public void setConcentrationName(String concentrationName) {
        this.concentrationName = concentrationName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
    
}
