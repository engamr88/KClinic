/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.session;

import com.os.util.hjpf.view.ColumnModel;
import com.os.util.hjpf.view.ViewAbstract;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mohamed
 */
@ManagedBean(name = "sessionView")
@ViewScoped
public class SessionView extends ViewAbstract<SessionModelDAO> {

   

    public SessionView() {
        super(new SessionModelDAO(), "com.os.ks.work.session.messages.Session");
    }

    @Override
    public SessionModelDAO getDAO() {
        //  (SessionModelDAO) dao.getModelWrapper().
        return (SessionModelDAO) dao;
    }

    @Override
    public List<ColumnModel> getColumns() {
        List<ColumnModel> cols = super.getColumns();
        Map colsOptions = new HashMap();
        //  colsOptions.put(WIDTH, "5,20,20,20,15,15");
        colsOptions.put(STYLE, "hidden-phone,col2,col3");
        reGenerateCols(cols, colsOptions);
        return cols;
    }

    @Override
    protected void beforeUpdate() {

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
