/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.doctor;

import com.os.models.User;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.util.Date;

/**
 *
 * @author egyptianeagle
 */
public class DoctorModelWrapper extends AbstractModelWrapper<User> {

    public DoctorModelWrapper() {
        initModel();
    }
    private Integer id;
    private String doctorUserName;
    private String doctorPassword;
    private String doctorFullName;
    private String doctorMobile;
    private String doctorPhone;
    private String doctorAddress;
    private String doctorEmail;
    private Integer doctorGender;
    private Integer doctorNationalNumber;
    private Date doctorBirthdate;

    public DoctorModelWrapper(Integer id, String doctorFullName) {
        this.id = id;
        this.doctorFullName = doctorFullName;
    }

    public DoctorModelWrapper(Integer id, String doctorFullName, String doctorMobile, String doctorAddress) {
        this.id = id;
        this.doctorFullName = doctorFullName;
        this.doctorMobile = doctorMobile;
        this.doctorAddress = doctorAddress;
    }

    public DoctorModelWrapper(Integer id, String doctorUserName, String doctorPassword) {
        this.id = id;
        this.doctorUserName = doctorUserName;
        this.doctorPassword = doctorPassword;
    }

    @Override
    public User getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        setModel(new User());
    }

    
    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public String getDoctorUserName() {
        return doctorUserName;
    }

    public void setDoctorUserName(String doctorUserName) {
        this.doctorUserName = doctorUserName;
    }



    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public String getDoctorMobile() {
        return doctorMobile;
    }

    public void setDoctorMobile(String doctorMobile) {
        this.doctorMobile = doctorMobile;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public Integer getDoctorGender() {
        return doctorGender;
    }

    public void setDoctorGender(Integer doctorGender) {
        this.doctorGender = doctorGender;
    }

    public Integer getDoctorNationalNumber() {
        return doctorNationalNumber;
    }

    public void setDoctorNationalNumber(Integer doctorNationalNumber) {
        this.doctorNationalNumber = doctorNationalNumber;
    }

    public Date getDoctorBirthdate() {
        return doctorBirthdate;
    }

    public void setDoctorBirthdate(Date doctorBirthdate) {
        this.doctorBirthdate = doctorBirthdate;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }
}
