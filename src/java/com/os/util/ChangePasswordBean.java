/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util;


import com.os.models.User;
import com.os.ks.work.user.LoginModelView;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.os.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Eng Amr
 */
@ManagedBean(name = "changePassBean")
@ViewScoped
public class ChangePasswordBean implements Serializable {

    private Session session;
    private Transaction tx;
    private User selectedUser = new User();
    private String currentPass = new String();
    private String newPass = new String();
    private String newConfirmPass = new String();
    private String decryptedPass = new String();
    private ResourceBundle res;

    public ChangePasswordBean() throws Exception {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        LoginModelView loginBus = (LoginModelView) httpsession.getAttribute("loginBean");
        selectedUser = CommonUtil.fetchLogginedUser();
        decryptedPass = CommonUtil.decrypt(selectedUser.getUserPassword());
        LoadMessage();
    }

    public void LoadMessage() {
        res = ResourceBundle.getBundle("com.os.common.messages.ChangePassword",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public boolean validate() {
        boolean flag = true;
        if (currentPass == null || currentPass.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("passwordsdata:currentPass", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", res.getString("currentPassEnter")));
            flag = false;
        } else if (newPass == null || newPass.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("passwordsdata:newPass", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", res.getString("newPassEnter")));
            flag = false;
        } else if (newConfirmPass == null || newConfirmPass.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("passwordsdata:confirmNewPass", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", res.getString("confirmPassEnter")));
            flag = false;
        } else if (!currentPass.equals(decryptedPass)) {
            FacesContext.getCurrentInstance().addMessage("passwordsdata:currentPass", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", res.getString("wrongCurrentPass")));
            flag = false;
        } else if (!newPass.equals(newConfirmPass)) {
            FacesContext.getCurrentInstance().addMessage("passwordsdata:confirmNewPass", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", res.getString("passMatch")));
            flag = false;
        }
        return flag;
    }

    public void updatePass() throws Exception {
        if (validate()) {
            try {
                beginTranscation();
                selectedUser.setUserPassword(CommonUtil.encrypt(newPass));
                session.update(selectedUser);
                tx.commit();
                session.close();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", res.getString("passUpdateSuccess")));

//                FacesContext.getCurrentInstance().getExternalContext().redirect("../../");

            } catch (IOException ex) {
                Logger.getLogger(ChangePasswordBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void beginTranscation() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewConfirmPass() {
        return newConfirmPass;
    }

    public void setNewConfirmPass(String newConfirmPass) {
        this.newConfirmPass = newConfirmPass;
    }
}
