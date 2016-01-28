package com.os.util.hjpf.view;

import java.util.Locale;
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "localeBean")
@SessionScoped
public class LocaleSessionBean {

    private String dirction = "RTL";
    private Locale locale = new Locale("ar");
    private String align = "right";
    private String invAlign = "left";
    private String ParentMenuTitle;
    private String CurrentMenuTitle;
    private String parentItem;
    private String currentItem;

    private void reloadPage() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        /* requestContext.execute("alert('RELOAD')");*/
        requestContext.execute("document.location.reload();");

    }

    public void arabicAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale("ar"));
        locale = new Locale("ar");

        dirction = "RTL";
        align = "right";
        invAlign = "left";
        reloadPage();






        /*  try {
         //   selectedIndex = Integer.parseInt(pageIndex);
         HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
         //            HttpServletRequest httpReq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
         response.sendRedirect("../templates/admin_template.xhtml");
         // context.getExternalContext().redirect("default.jspx");
         } catch (IOException ex) {
         ex.getMessage();
         }*/


    }

    public void englishAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.ENGLISH);
        locale = Locale.ENGLISH;

        dirction = "LTR";

        align = "left";
        invAlign = "right";
        reloadPage();
    }

    public String getDirction() {
        
        if(dirction.equals( "RTL")){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale("ar"));
        }
        return dirction;
    }

    public void setDirction(String dirction) {
        this.dirction = dirction;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getInvAlign() {
        return invAlign;
    }

    public void setInvAlign(String invAlign) {
        this.invAlign = invAlign;
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    public String getParentMenuTitle() {
        return ParentMenuTitle;
    }

    public void setParentMenuTitle(String ParentMenuTitle) {
        this.ParentMenuTitle = ParentMenuTitle;
    }

    public String getCurrentMenuTitle() {
        return CurrentMenuTitle;
    }

    public void setCurrentMenuTitle(String CurrentMenuTitle) {
        this.CurrentMenuTitle = CurrentMenuTitle;
    }

    public String getParentItem() {
        return parentItem;
    }

    public void setParentItem(String parentItem) {
        this.parentItem = parentItem;
    }

    public String getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(String currentItem) {
        this.currentItem = currentItem;
    }
    
}
