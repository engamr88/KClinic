/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.quest;

import com.os.ks.work.category.CategoryModelDAO;
import com.os.ks.work.category.CategoryModelWrapper;
import com.os.models.Category;
import com.os.util.CommonUtil;
import com.os.util.hjpf.view.ColumnModel;
import com.os.util.hjpf.view.ViewAbstract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mohamed
 */
@ManagedBean(name = "questView")
@ViewScoped
public class QuestView extends ViewAbstract<QuestModelDAO> {

    private Integer categoryId = -1;
    private List<CategoryModelWrapper> categoryList = new ArrayList<>();

    public QuestView() {
        super(new QuestModelDAO(), "com.os.ks.work.quest.messages.Quest");
        categoryList = new CategoryModelDAO().loadCategoryWrapperList();
    }

    @Override
    public QuestModelDAO getDAO() {
        //  (QuestModelDAO) dao.getModelWrapper().
        return (QuestModelDAO) dao;
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
    protected void beforeSave() {
        Category selectedCategory = new CategoryModelDAO().loadCategoryById(categoryId);
        dao.getModelWrapper().getModel().setCategory(selectedCategory);
    }

    @Override
    protected void clearForm() {
        super.clearForm(); //To change body of generated methods, choose Tools | Templates.
        categoryId = -1;
    }

    @Override
    protected void afterLoad() {
        categoryId = dao.getModelWrapper().getModel().getCategory().getCategoryId();
    }

    @Override
    protected boolean validateSave() {
     
        return validate();
    }

    @Override
    protected boolean validateUpdate() {
        return validate();
    }

    private boolean validate() {
        if (categoryId == -1) {
            CommonUtil.ViewValidationMessage("form:category", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("slcCategoryReq"));
            return false;
        }
        return true;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryModelWrapper> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryModelWrapper> categoryList) {
        this.categoryList = categoryList;
    }

}
