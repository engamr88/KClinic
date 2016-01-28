/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.user;

import com.os.util.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author abdallahgaber
 */
@ManagedBean(name = "loginModelView")
@SessionScoped
public class LoginModelView {

    private String userName;
    private String userPassword;
    private UserModelWrapper loginedUser = new UserModelWrapper();
    private UserModelDAO dao = new UserModelDAO();
    private boolean loginflag = false;
    private int userType;
    private ResourceBundle res;
    private int count = 0;
    private Date now;

    public LoginModelView() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        httpsession.setAttribute("auth", false);
        res = ResourceBundle.getBundle("com.os.ks.work.user.messages.User");
    }

    /**
     *
     * @throws Exception
     */
    /*  public LoginModelView() throws Exception {
        

     beginTranscation();
     locations = session.createQuery("from Location l").list();
     if (!locations.isEmpty()) {
     for (Location l : locations) {
     if (l.getLocationCode().equals("visit")) {
     l.setHomeVisit(l.getHomeVisit() + 1);
     session.update(l);
     tx.commit();
     } else {
     l.setLocationCode("visit");
     l.setHomeVisit(1);
     session.save(l);
     tx.commit();
     }
     }
     } else {
     location.setLocationCode("visit");
     location.setHomeVisit(1);
     session.save(location);
     tx.commit();
     }
     //          autoLogin();
     //        checkLogin();
     }*/
//**********************************************************************************************************************
    public void redirect(String s1, String s2, String s3) {
        CommonUtil.redirect(s1, s2, s3);
    }

    public void redirectForViewForms(String s2, String s3) {
        CommonUtil.redirect(s2, s3);
    }

    public void redirect(String s1, String s2, String s3, String s4) {
        CommonUtil.redirect(s1, s2, s3, s4);
    }

    public void checkLogin() throws Exception {
        now = new Date();
        if (validateLogin()) {
            try {
//                getRolesForUser();
                userType = loginedUser.getUserType();
                HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
                        getExternalContext().getSession(false);
                    if (loginedUser.getUserType() == 1) {
                        loginflag = true;
                        httpsession.setAttribute("auth", true);
                        backupDataWithDatabase();
                        FacesContext.getCurrentInstance().getExternalContext().redirect(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + "/secure/landingPage/landingPage.xhtml");
                    }
                     else if (loginedUser.getUserType() == 3) {
                        loginflag = true;
                        httpsession.setAttribute("auth", true);
                        backupDataWithDatabase();
                        FacesContext.getCurrentInstance().getExternalContext().redirect(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + "/secure/nurse/nurseIndex.xhtml");
                    }
                    else if (loginedUser.getUserType() == 3) {
                        loginflag = true;
                        httpsession.setAttribute("auth", true);
                        backupDataWithDatabase();
                        FacesContext.getCurrentInstance().getExternalContext().redirect(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + "/secure/nurse/nurseIndex.xhtml");
                    }
               
            } catch (IOException ex) {
                Logger.getLogger(LoginModelView.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Wrong Username or Password .."));
        }

    }

    public boolean backupDataWithDatabase() {
        boolean status = false;
        boolean downnloadbtn = false;
        File theDir = new File("D:/AppData");

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                
            }
        }
        try {
            Process p = null;

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String filepath = "backup(with_DB)-ksplaystation.sql";
            String backupPath = "";
            String batchCommand = "";
//            String realPath = CommonUtil.getRealPath("/resources/uploads/") + "/";
            String realPath = theDir.getAbsolutePath();
            //Backup with database
          //  batchCommand = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.1\\bin\\mysqldump -h localhost --port 3306 -u root --password=root --add-drop-database -B ksplaystation -r \"" + realPath + "/" + filepath + "\"";
            batchCommand = "mysqldump -h localhost --port 3306 -u root --password=root --add-drop-database -B ksplaystation -r \"" + realPath + "/" + filepath + "\"";

            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(batchCommand);
            int processComplete = p.waitFor();

            if (processComplete == 0) {
                status = true;
                InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/uploads/backup(with_DB)-aquaEgypt.sql");
                DefaultStreamedContent file = new DefaultStreamedContent(stream, "image/jpg", "backup(with_DB)-aquaEgypt.sql");
                downnloadbtn = true;
               
            } else {
                status = false;

               
            }

        } catch (IOException ioe) {
            
        } catch (Exception e) {
          
        }
        return status;
    }


    public boolean validateLogin() throws Exception {
     
        String enctyptpassword = CommonUtil.encrypt(userPassword);
        UserModelWrapper queryResult;
        queryResult = dao.fetchLoginedUser(userName, enctyptpassword);
        if (queryResult == null || queryResult.getId() == null) {
            return false;
        }
        loginedUser = queryResult;
        return true;
    }

    public void changepass() {
    }

    public void logout() {
        loginflag = false;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        // print out the session id
        if (httpSession != null) {

            synchronized (httpSession) {
                // invalidating a session destroys it
                httpSession.invalidate();

            }
        }

        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + "/login/loginIndex.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginModelView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void logoutStudent() {
        loginflag = false;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        // print out the session id
        if (httpSession != null) {

            synchronized (httpSession) {
                // invalidating a session destroys it
                httpSession.invalidate();

            }
        }

        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + "/login/customLogin.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginModelView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public UserModelWrapper getLoginedUser() {
        return loginedUser;
    }

    public void setLoginedUser(UserModelWrapper loginedUser) {
        this.loginedUser = loginedUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isLoginflag() {
        return loginflag;
    }

    public void setLoginflag(boolean loginflag) {
        this.loginflag = loginflag;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
