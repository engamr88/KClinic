/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.patient;

import com.os.models.Patient;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.util.Date;

/**
 *
 * @author egyptianeagle
 */
public class PatientModelWrapper extends AbstractModelWrapper<Patient> {

    public PatientModelWrapper() {
        initModel();
    }
    private Integer id;
    private String patientFullName;
    private String patientMobile;
    private String patientPhone;
    private String patientAddress;
    private String patientEmail;
    private Integer patientGender;
    private Integer patientNationalNumber;
    private Date patientBirthdate;
    private String patientJob;

    public PatientModelWrapper(Integer id, String patientFullName) {
        this.id = id;
        this.patientFullName = patientFullName;
    }

    public PatientModelWrapper(Integer id, String patientFullName, String patientMobile, String patientAddress) {
        this.id = id;
        this.patientFullName = patientFullName;
        this.patientMobile = patientMobile;
        this.patientAddress = patientAddress;
    }

    public PatientModelWrapper(Integer id, String patientFullName, String patientMobile, String patientPhone, String patientAddress, String patientEmail, Integer patientGender, Integer patientNationalNumber, Date patientBirthdate) {
        this.id = id;
        this.patientFullName = patientFullName;
        this.patientMobile = patientMobile;
        this.patientPhone = patientPhone;
        this.patientAddress = patientAddress;
        this.patientEmail = patientEmail;
        this.patientGender = patientGender;
        this.patientNationalNumber = patientNationalNumber;
        this.patientBirthdate = patientBirthdate;
    }

    public PatientModelWrapper(Integer id, String patientFullName, String patientMobile, String patientPhone, String patientAddress, String patientEmail, Integer patientGender, Integer patientNationalNumber, Date patientBirthdate, String patientJob) {
        this.id = id;
        this.patientFullName = patientFullName;
        this.patientMobile = patientMobile;
        this.patientPhone = patientPhone;
        this.patientAddress = patientAddress;
        this.patientEmail = patientEmail;
        this.patientGender = patientGender;
        this.patientNationalNumber = patientNationalNumber;
        this.patientBirthdate = patientBirthdate;
        this.patientJob = patientJob;
    }

   

    @Override
    public Patient getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Patient patient = new Patient();
        setModel(new Patient());
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public Integer getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(Integer patientGender) {
        this.patientGender = patientGender;
    }

    public Integer getPatientNationalNumber() {
        return patientNationalNumber;
    }

    public void setPatientNationalNumber(Integer patientNationalNumber) {
        this.patientNationalNumber = patientNationalNumber;
    }

    public Date getPatientBirthdate() {
        return patientBirthdate;
    }

    public void setPatientBirthdate(Date patientBirthdate) {
        this.patientBirthdate = patientBirthdate;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientJob() {
        return patientJob;
    }

    public void setPatientJob(String patientJob) {
        this.patientJob = patientJob;
    }
    
}
