package com.os.models;
// Generated Jan 12, 2016 5:08:18 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Detection generated by hbm2java
 */
public class Detection  implements java.io.Serializable {


     private Integer detectionId;
     private Patient patient;
     private String note;
     private Double extraFees;
     private Integer archive;
     private Date archiveDate;
     private Double discountAmount;
     private Doctor doctor;
     private Set<Session> sessions = new HashSet<Session>(0);
     private Set<DetectionStudy> detectionStudies = new HashSet<DetectionStudy>(0);
     private Set<Prescription> prescriptions = new HashSet<Prescription>(0);

    public Detection() {
    }

	
    public Detection(Patient patient, Integer archive) {
        this.patient = patient;
        this.archive = archive;
    }
    public Detection(Patient patient, String note, Double extraFees, Integer archive, Date archiveDate, Double discountAmount,Doctor doctor, Set<Session> sessions, Set<DetectionStudy> detectionStudies, Set<Prescription> prescriptions) {
       this.patient = patient;
       this.note = note;
       this.extraFees = extraFees;
       this.archive = archive;
       this.archiveDate = archiveDate;
       this.discountAmount = discountAmount;
       this.doctor=doctor;
       this.sessions = sessions;
       this.detectionStudies = detectionStudies;
       this.prescriptions = prescriptions;
    }
   
    public Integer getDetectionId() {
        return this.detectionId;
    }
    
    public void setDetectionId(Integer detectionId) {
        this.detectionId = detectionId;
    }
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public Double getExtraFees() {
        return this.extraFees;
    }
    
    public void setExtraFees(Double extraFees) {
        this.extraFees = extraFees;
    }
    public Integer getArchive() {
        return this.archive;
    }
    
    public void setArchive(Integer archive) {
        this.archive = archive;
    }
    public Date getArchiveDate() {
        return this.archiveDate;
    }
    
    public void setArchiveDate(Date archiveDate) {
        this.archiveDate = archiveDate;
    }
    public Double getDiscountAmount() {
        return this.discountAmount;
    }
    
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }
    public Set<Session> getSessions() {
        return this.sessions;
    }
    
    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
    public Set<DetectionStudy> getDetectionStudies() {
        return this.detectionStudies;
    }
    
    public void setDetectionStudies(Set<DetectionStudy> detectionStudies) {
        this.detectionStudies = detectionStudies;
    }
    public Set<Prescription> getPrescriptions() {
        return this.prescriptions;
    }
    
    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}


