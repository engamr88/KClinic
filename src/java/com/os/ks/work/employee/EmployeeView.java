/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.employee;

import com.os.ks.work.user.UserModelDAO;
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
@ManagedBean(name = "employeeView")
@ViewScoped
public class EmployeeView extends ViewAbstract<UserModelDAO> {

    private boolean showPass;

    public EmployeeView() {
        super(new UserModelDAO(), "com.os.ks.work.user.messages.Employee");
        showPass = !isUpdateMode();
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

    @Override
    protected void beforeSave() {
        try {
            dao.getModelWrapper().getModel().setUserType(3);
            dao.getModelWrapper().getModel().setUserPassword(CommonUtil.encrypt(dao.getModelWrapper().getModel().getUserPassword()));
        } catch (Exception ex) {
            Logger.getLogger(EmployeeView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void beforeUpdate() {
        try {
            dao.getModelWrapper().getModel().setUserPassword(CommonUtil.encrypt(dao.getModelWrapper().getModel().getUserPassword()));
        } catch (Exception ex) {
            Logger.getLogger(EmployeeView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void afterLoad() {
        try {
            showPass = !isUpdateMode();
            dao.getModelWrapper().getModel().setUserPassword(CommonUtil.decrypt(dao.getModelWrapper().getModel().getUserPassword()));
        } catch (Exception ex) {
            Logger.getLogger(EmployeeView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void clear() {
        super.clear(); //To change body of generated methods, choose Tools | Templates.
        showPass = !isUpdateMode();
    }

    public boolean validate() {
        if (!dao.checkDuplication("userId",
                dao.getModelWrapper().getModel().getUserId(),
                "userName",
                dao.getModelWrapper().getModel().getUserName(),
                null, "User")) {
            CommonUtil.ViewValidationMessage("form:userName", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("userExisted"));
            return false;
        }

        if (!CommonUtil.isNumeric(dao.getModelWrapper().getModel().getUserMobile())) {
            CommonUtil.ViewValidationMessage("form:userMobile", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("phoneNumNumericReq"));
            return false;
        }
        if (dao.getModelWrapper().getModel().getUserPhone() != null && !dao.getModelWrapper().getModel().getUserPhone().isEmpty()) {
            if (!CommonUtil.isNumeric(dao.getModelWrapper().getModel().getUserPhone())) {
                CommonUtil.ViewValidationMessage("form:userPhone", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("phoneNumNumericReq"));
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean validateSave() {
        return validate();
    }

    @Override
    protected boolean validateUpdate() {
        return validate();
    }

    public boolean isShowPass() {
        return showPass;
    }

    public void setShowPass(boolean showPass) {
        this.showPass = showPass;
    }

}
