/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.doctor;

import com.os.ks.work.user.DoctorModelDAO;
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
 * @author mohamed
 */
@ManagedBean(name = "doctorView")
@ViewScoped
public class DoctorView extends ViewAbstract<DoctorModelDAO> {
    
    private boolean showPass;

    public DoctorView() {
        //com.os.ks.work.user.messages.User
        super(new DoctorModelDAO(), "com.os.ks.work.doctor.messages.Doctor");
        showPass = !isUpdateMode();
    }
    
    @Override
    public DoctorModelDAO getDAO() {
        //  (DoctorModelDAO) dao.getModelWrapper().
        return (DoctorModelDAO) dao;
    }
    
    @Override
    public List<ColumnModel> getColumns() {
        List<ColumnModel> cols = super.getColumns();
        Map colsOptions = new HashMap();
        //  colsOptions.put(WIDTH, "5,20,20,20,15,15");
        colsOptions.put(STYLE, "hidden,hidden,hidden,hidden,col5,col6,col7,col8,hidden,hidden,hidden");
        reGenerateCols(cols, colsOptions);
        return cols;
    }
    
    @Override
    protected void beforeSave() {
        try {
            dao.getModelWrapper().getModel().setUserType(2);
            dao.getModelWrapper().getModel().setUserPassword(CommonUtil.encrypt(dao.getModelWrapper().getModel().getUserPassword()));
        } catch (Exception ex) {
            Logger.getLogger(DoctorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void beforeUpdate() {
        try {
            dao.getModelWrapper().getModel().setUserPassword(CommonUtil.encrypt(dao.getModelWrapper().getModel().getUserPassword()));
        } catch (Exception ex) {
            Logger.getLogger(DoctorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void afterLoad() {
        try {
            showPass = !isUpdateMode();
            dao.getModelWrapper().getModel().setUserPassword(CommonUtil.decrypt(dao.getModelWrapper().getModel().getUserPassword()));
        } catch (Exception ex) {
            Logger.getLogger(DoctorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void clear() {
        super.clear(); //To change body of generated methods, choose Tools | Templates.
        showPass = !isUpdateMode();
    }
    
    public boolean validate() {
        if (!dao.checkDuplication("doctorId",
                dao.getModelWrapper().getModel().getUserId(),
                "doctorUserName",
                dao.getModelWrapper().getModel().getUserName(),
                null, "Doctor")) {
            CommonUtil.ViewValidationMessage("form:doctorName", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("doctorExisted"));
            return false;
        }
        
        if (!CommonUtil.isNumeric(dao.getModelWrapper().getModel().getUserMobile())) {
            CommonUtil.ViewValidationMessage("form:doctorMobile", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("phoneNumNumericReq"));
            return false;
        }
        if (dao.getModelWrapper().getModel().getUserPhone() != null && !dao.getModelWrapper().getModel().getUserPhone().isEmpty()) {
            if (!CommonUtil.isNumeric(dao.getModelWrapper().getModel().getUserPhone())) {
                CommonUtil.ViewValidationMessage("form:doctorPhone", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("phoneNumNumericReq"));
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
