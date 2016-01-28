/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.detection;

import com.os.models.Detection;
import com.os.models.Doctor;
import com.os.models.Patient;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.util.Date;

/**
 *
 * @author egyptianeagle
 */
public class DetectionModelWrapper extends AbstractModelWrapper<Detection> {

    public DetectionModelWrapper() {
        initModel();
    }
    private Integer id;
    private String patientFullName;
    private String note;
    private Double extraFees;
    private boolean archive;
    private Date archiveDate;
    private Double discountAmount;
    private String doctorFullName;

    public DetectionModelWrapper(Integer id, String patientFullName, String note, String doctorFullName, Double extraFees) {
        this.id = id;
        this.patientFullName = patientFullName;
        this.note = note;
        this.doctorFullName = doctorFullName;
        this.extraFees = extraFees;
    }

    public DetectionModelWrapper(Integer id, String patientFullName, String note, Double extraFees, boolean archive, Date archiveDate, Double discountAmount) {
        this.id = id;
        this.patientFullName = patientFullName;
        this.note = note;
        this.extraFees = extraFees;
        this.archive = archive;
        this.archiveDate = archiveDate;
        this.discountAmount = discountAmount;
    }

    @Override
    public Detection getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Detection detection = new Detection();
        detection.setDoctor(new Doctor());
        detection.setPatient(new Patient());
        setModel(detection);
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getExtraFees() {
        return extraFees;
    }

    public void setExtraFees(Double extraFees) {
        this.extraFees = extraFees;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public Date getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(Date archiveDate) {
        this.archiveDate = archiveDate;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

}
