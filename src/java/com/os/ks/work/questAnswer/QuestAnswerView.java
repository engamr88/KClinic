/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.questAnswer;


import com.os.util.hjpf.view.ColumnModel;
import com.os.util.hjpf.view.ViewAbstract;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mohamed
 */
@ManagedBean(name = "questAnswerView")
@ViewScoped
public class QuestAnswerView extends ViewAbstract<QuestAnswerModelDAO> {

  
    public QuestAnswerView() {
        super(new QuestAnswerModelDAO(), "com.os.ks.work.questAnswer.messages.QuestAnswer");
    }


    @Override
    public QuestAnswerModelDAO getDAO() {
        //  (QuestAnswerModelDAO) dao.getModelWrapper().
        return (QuestAnswerModelDAO) dao;
    }

    @Override
    public List<ColumnModel> getColumns() {
        List<ColumnModel> cols = super.getColumns();
        Map colsOptions = new HashMap();
        //  colsOptions.put(WIDTH, "5,20,20,20,15,15");
        colsOptions.put(STYLE, "hidden-phone,col2,col3,hidden,hidden-phone,hidden,hidden,hidden");
        reGenerateCols(cols, colsOptions);
        return cols;
    }

    public void logout() {
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
            Logger.getLogger(QuestAnswerView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    @Override
    protected void afterCopy() {
    }

    @Override
    protected void afterLoad() {
     
    }

  

    @Override
    protected boolean validateSave() {

        return true;
    }

    @Override
    protected boolean validateUpdate() {
        return true;
    }
}
