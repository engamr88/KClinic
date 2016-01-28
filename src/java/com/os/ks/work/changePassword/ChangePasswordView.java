/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.changePassword;

import com.os.models.User;
import com.os.ks.work.user.UserModelDAO;
import com.os.ks.work.user.UserModelWrapper;
import com.os.util.CommonUtil;
import com.os.util.hjpf.view.ColumnModel;
import com.os.util.hjpf.view.ViewAbstract;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Eng Amr Nabil
 */
@ManagedBean(name = "changePasswordView")
@ViewScoped
public class ChangePasswordView extends ViewAbstract<UserModelDAO> {

    UserModelWrapper loggedInUserWrapper = new UserModelWrapper();
    private String userName;
    private String oldPassword;
    private String newPassword;
    private String newPasswordRetype;

    public ChangePasswordView() {
        super(new UserModelDAO(), "com.os.ks.work.changePassword.messages.ChangePassword");
        loggedInUserWrapper = dao.fetchAdmin();
        userName = loggedInUserWrapper.getUserName();
        setDatableVisiable(false);
        setUpdateMode(true);
    }

    @Override
    public List<ColumnModel> getColumns() {
        List<ColumnModel> cols = super.getColumns();
        Map colsOptions = new HashMap();
        //  colsOptions.put(WIDTH, "5,20,20,20,15,15");
        colsOptions.put(STYLE, "hidden,hidden,hidden,hidden,col2,col3,hidden,col5,hidden,hidden,hidden,hidden");
        reGenerateCols(cols, colsOptions);
        return cols;
    }

    @Override
    public UserModelDAO getDAO() {
        return (UserModelDAO) dao;
    }

    public boolean validate() {
        try {

            if (!oldPassword.equals(CommonUtil.decrypt(loggedInUserWrapper.getUserPassword()))) {
                CommonUtil.ViewValidationMessage("form:oldPassword", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("oldPassFail"));
                return false;
            }
            if (!newPassword.equals(newPasswordRetype)) {
                CommonUtil.ViewValidationMessage("form:newPasswordRetype", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("passMatchErr"));
                return false;
            }

        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    protected void beforeUpdate() {
        try {
            // load new data to be updated
           User user = dao.fetchAdminModel();
            dao.getModelWrapper().setModel(user);
            dao.getModelWrapper().getModel().setUserName(userName);
            dao.getModelWrapper().getModel().setUserPassword(CommonUtil.encrypt(newPassword));
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected boolean validateSave() {
        return validate();
    }

    @Override
    protected boolean validateUpdate() {
        return validate();
    }

    public UserModelWrapper getLoggedInUserWrapper() {
        return loggedInUserWrapper;
    }

    public void setLoggedInUserWrapper(UserModelWrapper loggedInUserWrapper) {
        this.loggedInUserWrapper = loggedInUserWrapper;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRetype() {
        return newPasswordRetype;
    }

    public void setNewPasswordRetype(String newPasswordRetype) {
        this.newPasswordRetype = newPasswordRetype;
    }
}
