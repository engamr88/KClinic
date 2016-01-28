/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util;

import com.os.models.User;
import com.os.ks.work.user.LoginModelView;
import com.os.ks.work.user.UserModelDAO;
import com.os.ks.work.user.UserModelWrapper;
import com.os.util.hjpf.view.LocaleSessionBean;
import com.os.util.hjpf.view.Navigator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.util.Base64;

/**
 *
 * @author mohamed
 */
public class CommonUtil {

    static HashMap<Integer, String> weekDays;
    static ResourceBundle res;

    private static String keyst = "e8ffc7e56311679f1bd8fc91aa77a5eb";
    private static byte[] key;
    private static ResourceBundle menuRes;
    public static final String TIME_SERVER = "time-a.nist.gov";

    public static void ViewGloabalMessage(Severity severity, String summary, String detials) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detials));
    }

    public static void ViewGloabalMessage(String compID, Severity severity, String summary, String detials) {
        FacesContext.getCurrentInstance().addMessage(compID, new FacesMessage(severity, summary, detials));
    }

    public static void ViewValidationMessage(String compID, Severity severity, String summary, String detials) {
        FacesContext.getCurrentInstance().addMessage(compID, new FacesMessage(severity, summary, detials));
    }

    public static HttpSession getCurrentHTTPSession() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return httpsession;
    }

    public static Navigator getNavigator() {
        Navigator nav = (Navigator) FacesContext.getCurrentInstance().getViewRoot().getViewMap().get("navigator");
      

        return nav;

    }

    public static void copyFile(String fileName, InputStream in, String destination) {

        try {
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int read;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {

        }
    }

    public static String getRealPath(String path) {
        return (((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath(path));
    }

    //===================================== encription and decription ===============
    public static byte[] convertHexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String encrypt(String plainPassword) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        key = convertHexToBytes(keyst);
        final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        final String encryptedString = Base64.encodeBase64String(cipher.doFinal(plainPassword.getBytes("UTF8")));
        String passwordEncrypted = encryptedString.trim();

        return passwordEncrypted;

    }

    public static String decrypt(String encryptPassword) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        key = convertHexToBytes(keyst);
        final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        String encryptedString = new String(cipher.doFinal(Base64.decodeBase64(encryptPassword.getBytes())), "UTF-8");
        String passwordDecrypted = encryptedString.trim();

        return passwordDecrypted;

    }
//    ****************************************** redirecton**********************************************

    public static void redirect(String viewKey, String parentMenuTitle, String currentMenuItem) {
        /* HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
         getExternalContext().getSession(false);*/
        HttpServletResponse httpRes = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest httpReq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpsession = httpReq.getSession(false);
        LoginModelView loginBus = (LoginModelView) httpsession.getAttribute("loginModelView");
        /*   userGroupsList = session.createQuery("from GroupRole gr join fetch gr.groups g"
         + " where gr.roleName like '" + viewKey + "' and g.groupId in (select g2.groupId from UserGroups ug join ug.groups g2 join ug.user u"
         + " where u.userId=" + loginBus.getLoginedUser().getId()+ ")").list();*/

        menuRes = ResourceBundle.getBundle("com.os.common.messages.MenuMessages",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
        LocaleSessionBean localeBean = (LocaleSessionBean) httpsession.getAttribute("localeBean");
        localeBean.setCurrentMenuTitle(currentMenuItem);
        localeBean.setParentMenuTitle(parentMenuTitle);
        localeBean.setParentItem(menuRes.getString(parentMenuTitle));
        localeBean.setCurrentItem(menuRes.getString(currentMenuItem));
    }

    public static void redirect(String parentMenuTitle, String currentMenuItem) {
        /* HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
         getExternalContext().getSession(false);*/
        HttpServletResponse httpRes = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest httpReq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpsession = httpReq.getSession(false);

        LocaleSessionBean localeBean = (LocaleSessionBean) httpsession.getAttribute("localeBean");
        menuRes = ResourceBundle.getBundle("com.os.common.messages.MenuMessages",
                localeBean.getLocale());

        localeBean.setCurrentMenuTitle(currentMenuItem);
        localeBean.setParentMenuTitle(parentMenuTitle);
        localeBean.setParentItem(menuRes.getString(parentMenuTitle));

        localeBean.setCurrentItem(menuRes.getString(currentMenuItem));

    }

    public static void redirect(String viewKey, String parentMenuTitle, String currentMenuItem, String maxKey) {
        int maxallaowForm = Integer.parseInt(maxKey);
        HttpServletResponse httpRes = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest httpReq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpsession = httpReq.getSession(false);
        LoginModelView loginBus = (LoginModelView) httpsession.getAttribute("loginModelView");

        /*   userGroupsList = session.createQuery("from GroupRole gr join fetch gr.groups g"
         + " where gr.roleName like '" + viewKey + "' and g.groupId in (select g2.groupId from UserGroups ug join ug.groups g2 join ug.user u"
         + " where u.userId=" + loginBus.getLoginedUser().getId()+ ")").list();*/
        menuRes = ResourceBundle.getBundle("com.os.common.messages.MenuMessages",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
        LocaleSessionBean localeBean = (LocaleSessionBean) httpsession.getAttribute("localeBean");
        localeBean.setCurrentMenuTitle(currentMenuItem);
        localeBean.setParentMenuTitle(parentMenuTitle);
        localeBean.setParentItem(menuRes.getString(parentMenuTitle));

        localeBean.setCurrentItem(menuRes.getString(currentMenuItem));

    }

    //        ************************************ create privilages **********************************
    public static User fetchLogginedUser() {
        UserModelDAO userDao = new UserModelDAO();
        User loginedUser = new User();
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        LoginModelView loginBus = (LoginModelView) httpsession.getAttribute("loginModelView");
        if (loginBus == null) {
            loginedUser = fetchSptLogginedUser();
        } else {
            loginedUser = userDao.fetchLoginedUser(loginBus.getLoginedUser().getId());
        }
        return loginedUser;
    }

    public static User fetchSptLogginedUser() {
        UserModelDAO userDao = new UserModelDAO();
        User loginedUser = new User();
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        LoginModelView loginBus = (LoginModelView) httpsession.getAttribute("loginModelView");
     
        if (loginBus == null) {
            loginedUser = fetchLogginedUser();
        } else {
            loginedUser = userDao.fetchLoginedUser(loginBus.getLoginedUser().getId());
        }
        return loginedUser;
    }

    public static UserModelWrapper getLoginedUserWrapper() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        LoginModelView loginBus = (LoginModelView) httpsession.getAttribute("loginModelView");
        return loginBus.getLoginedUser();
    }

    public static String getExtensionFile(String file) {
        String extension = "";

        int i = file.lastIndexOf('.');
        int p = Math.max(file.lastIndexOf('/'), file.lastIndexOf('\\'));

        if (i > p) {
            extension = file.substring(i + 1);
            return extension;
        }
        return "";
    }

    public static HashMap<Integer, String> getWeekDays() {
        return weekDays;
    }

    public static void setWeekDays(HashMap<Integer, String> weekDays) {
        CommonUtil.weekDays = weekDays;
    }

    public static boolean checkTimeValid() throws IOException {
        res = ResourceBundle.getBundle("com.os.common.messages.Common",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long returnTime = timeInfo.getReturnTime();
        Date time = new Date(returnTime);
        long systemtime = System.currentTimeMillis();
        timeInfo.computeDetails();
        Date realdate = new Date(systemtime + timeInfo.getOffset());

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm a");
        String localDate = DATE_FORMAT.format(time);
        String serverDate = DATE_FORMAT.format(realdate);
        if (!localDate.equals(serverDate)) {
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
                CommonUtil.ViewValidationMessage(null, FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("timePlayed"));
                return false;
            } catch (IOException ex) {
                Logger.getLogger(LoginModelView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public static boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
