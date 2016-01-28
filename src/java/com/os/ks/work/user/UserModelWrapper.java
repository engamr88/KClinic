/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.user;

import com.os.models.User;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author egyptianeagle
 */
public class UserModelWrapper extends AbstractModelWrapper<User> {

    public UserModelWrapper() {
        initModel();
    }
    private Integer id;
    private String userName;
    private String userPassword;
    private Integer userType;
    private String userFullName;
    private String userMobile;
    private String userPhone;
    private String userAddress;
    private String userEmail;
    private Integer userGender;
    private Integer userNationalNumber;
    private Date userBirthdate;

    public UserModelWrapper(Integer id, String userFullName) {
        this.id = id;
        this.userFullName = userFullName;
    }

    public UserModelWrapper(Integer id, String userFullName, String userMobile, String userAddress) {
        this.id = id;
        this.userFullName = userFullName;
        this.userMobile = userMobile;
        this.userAddress = userAddress;
    }

    public UserModelWrapper(Integer id, String userName, String userPassword, Integer userType) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
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
        User user = new User();
        setModel(new User());
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public Integer getUserNationalNumber() {
        return userNationalNumber;
    }

    public void setUserNationalNumber(Integer userNationalNumber) {
        this.userNationalNumber = userNationalNumber;
    }

    public Date getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(Date userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
