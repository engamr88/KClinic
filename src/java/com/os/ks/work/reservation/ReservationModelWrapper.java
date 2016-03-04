/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.reservation;

import com.os.models.Category;
import com.os.models.Patient;
import com.os.models.PriceList;
import com.os.models.Reservation;
import com.os.models.User;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.util.Date;

/**
 *
 * @author egyptianeagle
 */
public class ReservationModelWrapper extends AbstractModelWrapper<Reservation> {

    public ReservationModelWrapper() {
        initModel();
    }
    private Integer id;
    private String categoryTitle;
    private String patientFullName;
    private Integer reservationType;
    private Date reservationDate;
    private Integer reservationNumber;
    private String priceType;
    private Double priceAmount;
    private String doctorFullName;

    public ReservationModelWrapper(Integer id, String categoryTitle, String patientFullName, Integer reservationType, Date reservationDate, Integer reservationNumber, String priceType, Double priceAmount,String doctorFullName) {
        this.id = id;
        this.categoryTitle = categoryTitle;
        this.patientFullName = patientFullName;
        this.reservationType = reservationType;
        this.reservationDate = reservationDate;
        this.reservationNumber = reservationNumber;
        this.priceType = priceType;
        this.priceAmount = priceAmount;
        this.doctorFullName=doctorFullName;
    }

    @Override
    public Reservation getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Reservation reservation = new Reservation();
        reservation.setCategory(new Category());
        reservation.setPatient(new Patient());
        reservation.setPriceList(new PriceList());
        reservation.setDoctor(new User());
        setModel(reservation);
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public Integer getReservationType() {
        return reservationType;
    }

    public void setReservationType(Integer reservationType) {
        this.reservationType = reservationType;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Integer reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Double getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Double priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }
    

}
