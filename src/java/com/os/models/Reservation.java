package com.os.models;
// Generated Jan 12, 2016 5:08:18 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Reservation generated by hbm2java
 */
public class Reservation implements java.io.Serializable {

    private Integer reservationId;
    private Category category;
    private Patient patient;
    private PriceList priceList;
    private Integer reservationType;
    private Date reservationDate;
    private Integer reservationNumber;
    private User doctor;
    private boolean archive;
    private boolean current;

    public Reservation() {
    }

    public Reservation(Integer reservationId, Category category, Patient patient, PriceList priceList, Integer reservationType, Date reservationDate, Integer reservationNumber, User doctor, boolean archive, boolean current) {
        this.reservationId = reservationId;
        this.category = category;
        this.patient = patient;
        this.priceList = priceList;
        this.reservationType = reservationType;
        this.reservationDate = reservationDate;
        this.reservationNumber = reservationNumber;
        this.doctor = doctor;
        this.archive = archive;
        this.current = current;
    }

    public Integer getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PriceList getPriceList() {
        return this.priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public Integer getReservationType() {
        return this.reservationType;
    }

    public void setReservationType(Integer reservationType) {
        this.reservationType = reservationType;
    }

    public Date getReservationDate() {
        return this.reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getReservationNumber() {
        return this.reservationNumber;
    }

    public void setReservationNumber(Integer reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

}
