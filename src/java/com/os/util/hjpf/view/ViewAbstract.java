/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util.hjpf.view;

import com.os.util.CommonUtil;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author mohamed
 */
public abstract class ViewAbstract<T extends AbstractDAOWrapper> implements Serializable {

    //  public T selected;
    protected ResourceBundle res;
    protected ResourceBundle commonRes;
    protected T dao;
    protected LookUpView lookUpView;
    protected static String WIDTH = "width";
    protected static String STYLE = "style";
    protected LazyAbstractDataModel<T> lazyModel;
    private boolean updateMode = false;
    private boolean ajaxStatus = true;
    private boolean lookUpDialogVisibility = false;
    private boolean datableVisiable = true;
    private boolean datableEditVisiable = true;
    private boolean datablesaveMergeVisiable = false;
    private String formId = "form";
    private String dataTableFormId = "table-form";
    private boolean messagesVisible = true;
    private String lookUpformPath = "LookUpCompoments.xhtml";
    private String FORM_NAME_KEY;
    private String MESSAGE_BUNDLE;
    private boolean saveAndCont = false;
    private boolean updateAndCont = false;
    private boolean clearBtnVisible = true;
    private String resPath;

    public ViewAbstract(T dao, String resPath) {

        this.dao = dao;
        lazyModel = new LazyAbstractDataModel<>(dao);
        this.resPath = resPath;
        loadMessages();

        // navigator.setCurrentView(this);
        //  clearForm();
    }

    private void loadMessages() {

        commonRes = ResourceBundle.getBundle("com.os.common.messages.Common",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
        if (resPath != null) {
            if (CommonUtil.getNavigator().getCurrentView() == null) {
                CommonUtil.getNavigator().setCurrentView(this);
            }
            res = ResourceBundle.getBundle(resPath,
                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } else {

            res = commonRes;
        }

    }
    private List<ColumnModel> columns = new ArrayList<>();

    public List<ColumnModel> getColumns() {
        //    if (columns.isEmpty() && datableVisiable) {
        createCols();
        //   }
        return columns;
    }

    public List<ColumnModel> getCustomColumns() {
        if (columns.isEmpty()) {
            createCols();
        }
        return columns;
    }

    private void createCols() {
        loadMessages();
        columns = new ArrayList<>();
        String[] cols = dao.getModelWrapper().getDelaredFieldsString().split(",");
        for (int i = 0; i < cols.length; i++) {
            String s = cols[i];
            columns.add(new ColumnModel(res.getString(s), s));

        }
    }

    protected void reGenerateCols(List<ColumnModel> cols, Map colsOptions) {
        if (cols.size() > 0) {
            for (Iterator it = colsOptions.entrySet().iterator(); it.hasNext();) {
                Map.Entry e = (Map.Entry) it.next();
                String property = (String) e.getKey();
                String value = (String) e.getValue();
                String[] values = value.split(",");
                for (int i = 0; i < values.length; i++) {
                    String s = values[i];
                    if (property.equals(WIDTH)) {
                        cols.get(i).setWidth(s);
                        continue;
                    }

                    if (property.equals(STYLE)) {
                        cols.get(i).setStyle(s);
                        continue;
                    }

                }

            }
        }
    }

    public String rowSelected() {
        updateMode = true;
        dao.loadModelWrapper();
        afterLoad();
        return null;
    }

    public String copyRecord() {
        dao.loadModelWrapper();
        updateMode = false;
        afterLoad();
        afterCopy();
        return null;
    }

    protected void clearForm() {
        updateMode = false;
        dao.clear();
    }

    public abstract T getDAO();

    public void clear() {
        clearForm();
    }

    protected void beforeSave() {
    }

    protected void beforeSaveAndCont() {
    }

    protected void beforeUpdateAndCont() {
    }

    protected void beforeUpdate() {
    }

    protected void afterSave() {
    }

    protected void afterSaveAndCont() {
    }

    protected void afterUpdateAndCont() {
    }

    protected void afterUpdate() {
    }

    protected void afterLoad() {
    }

    protected void afterCopy() {
    }

    protected abstract boolean validateSave();

    protected abstract boolean validateUpdate();

    public void save() {
        //   dao.setModelWrapper(selected);
        if (validateSave()) {
            beforeSave();
            if (dao.save()) {
                afterSave();
                clearForm();
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("saveSuccessfulMsg"));
            } else {
                onSaveFail();
                String failMessage;
                try {
                    failMessage = res.getString("savefailMsg");
                } catch (Exception e) {
                    failMessage = commonRes.getString("savefailMsg");
                }
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", failMessage);
            }
        }
    }

    public void saveAndCont() {
        if (validateSave()) {
         
            beforeSaveAndCont();
            if (dao.save()) {
                afterSaveAndCont();
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("saveSuccessfulMsg"));
            } else {
                onSaveFail();
                String failMessage;
                try {
                    failMessage = res.getString("savefailMsg");
                } catch (Exception e) {
                    failMessage = commonRes.getString("savefailMsg");
                }
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", failMessage);
            }
        }
    }

    public Object saveMerge() {
        if (validateSave()) {
            beforeSave();
         return   dao.saveMerge();
        }
        return null;
    }

    protected void onSaveFail() {
    }

    public void update() {
        if (validateUpdate()) {
            beforeUpdate();
            if (dao.saveOrUpdate()) {
                afterUpdate();
                clearForm();
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("updateSuccessfulMsg"));
            } else {
                onUpdateFail();
                String failMessage;
                try {
                    failMessage = res.getString("savefailMsg");
                } catch (Exception e) {
                    failMessage = commonRes.getString("savefailMsg");
                }
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", failMessage);
            }
        }
    }

    public void updateAndContinue() {
        if (validateUpdate()) {
            beforeUpdateAndCont();
            if (dao.saveOrUpdate()) {
                afterUpdateAndCont();
                //   clearForm();
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("updateSuccessfulMsg"));
            } else {
                onUpdateFail();
                String failMessage;
                try {
                    failMessage = res.getString("savefailMsg");
                } catch (Exception e) {
                    failMessage = commonRes.getString("savefailMsg");
                }
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", failMessage);
            }
        }
    }

    public void onUpdateFail() {
    }

    public void delete() {
        dao.loadModelWrapper();
        if (dao.delete()) {
            clearForm();
            CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("deleteSuccessfulMsg"));
        } else {
            String failMessage;
            try {
                failMessage = res.getString("deletefailMsg");
            } catch (Exception e) {
                failMessage = commonRes.getString("deletefailMsg");
            }
            CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", failMessage);

        }
    }

    public LazyAbstractDataModel<T> getLazyModel() {

        return lazyModel;
    }

    /*public Navigator getNavigator() {
     return navigator;
     }

     public void setNavigator(Navigator navigator) {
     this.navigator = navigator;
     }*/
    public String getViewTtile() {

        return res.getString("title");

    }

    public boolean isUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(boolean updateMode) {
        this.updateMode = updateMode;
    }

    public boolean isAjaxStatus() {
        return ajaxStatus;
    }

    public void setAjaxStatus(boolean ajaxStatus) {
        this.ajaxStatus = ajaxStatus;
    }

    public boolean isLookUpDialogVisibility() {
        return lookUpDialogVisibility;
    }

    public void setLookUpDialogVisibility(boolean lookUpDialogVisibility) {
        this.lookUpDialogVisibility = lookUpDialogVisibility;
    }

    public LookUpView getLookUpView() {
        return lookUpView;
    }

    public void setLookUpView(LookUpView lookUpView) {
        this.lookUpView = lookUpView;
    }

    public void updateLookUp() {
    }

    ;

    public boolean isDatableVisiable() {
        return datableVisiable;
    }

    public void setDatableVisiable(boolean datableVisiable) {
        this.datableVisiable = datableVisiable;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getDataTableFormId() {
        return dataTableFormId;
    }

    public void setDataTableFormId(String dataTableFormId) {
        this.dataTableFormId = dataTableFormId;
    }

    public boolean isMessagesVisible() {
        return messagesVisible;
    }

    public void setMessagesVisible(boolean messagesVisible) {
        this.messagesVisible = messagesVisible;
    }

    public String getLookUpformPath() {
        return lookUpformPath;
    }

    public void setLookUpformPath(String lookUpformPath) {
        this.lookUpformPath = lookUpformPath;
    }

    public String getFORM_NAME_KEY() {
        return FORM_NAME_KEY;
    }

    public void setFORM_NAME_KEY(String FORM_NAME_KEY) {
        this.FORM_NAME_KEY = FORM_NAME_KEY;
    }

    public String getMESSAGE_BUNDLE() {
        return MESSAGE_BUNDLE;
    }

    public void setMESSAGE_BUNDLE(String MESSAGE_BUNDLE) {
        this.MESSAGE_BUNDLE = MESSAGE_BUNDLE;
    }

    public boolean isDatableEditVisiable() {
        return datableEditVisiable;
    }

    public void setDatableEditVisiable(boolean datableEditVisiable) {
        this.datableEditVisiable = datableEditVisiable;
    }

    public List getCommonList(int key) {
        return null;
    }

    public boolean isDatablesaveMergeVisiable() {
        return datablesaveMergeVisiable;
    }

    public void setDatablesaveMergeVisiable(boolean datablesaveMergeVisiable) {
        this.datablesaveMergeVisiable = datablesaveMergeVisiable;
    }

    public boolean isSaveAndCont() {
        return saveAndCont;
    }

    public void setSaveAndCont(boolean saveAndCont) {
        this.saveAndCont = saveAndCont;
    }

    public boolean isUpdateAndCont() {
        return updateAndCont;
    }

    public void setUpdateAndCont(boolean updateAndCont) {
        this.updateAndCont = updateAndCont;
    }

    public boolean isClearBtnVisible() {
        return clearBtnVisible;
    }

    public void setClearBtnVisible(boolean clearBtnVisible) {
        this.clearBtnVisible = clearBtnVisible;
    }

}
