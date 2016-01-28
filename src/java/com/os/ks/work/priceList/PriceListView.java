/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.priceList;

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
@ManagedBean(name = "priceListView")
@ViewScoped
public class PriceListView extends ViewAbstract<PriceListModelDAO> {

    private int categoryId;
    private List<CategoryModelWrapper> categoryList = new ArrayList<>();

    public PriceListView() {
        super(new PriceListModelDAO(), "com.os.ks.work.priceList.messages.PriceList");
        categoryId = -1;
        categoryList = new CategoryModelDAO().loadCategoryWrapperList();
    }

    @Override
    public PriceListModelDAO getDAO() {
        //  (PriceListModelDAO) dao.getModelWrapper().
        return (PriceListModelDAO) dao;
    }

    @Override
    public List<ColumnModel> getColumns() {
        List<ColumnModel> cols = super.getColumns();
        Map colsOptions = new HashMap();
        //  colsOptions.put(WIDTH, "5,20,20,20,15,15");
        colsOptions.put(STYLE, "hidden-phone,col2,col3,col4");
        reGenerateCols(cols, colsOptions);
        return cols;
    }

    @Override
    protected void clearForm() {
        super.clearForm(); //To change body of generated methods, choose Tools | Templates.
        categoryId = -1;
    }

    @Override
    protected void beforeSave() {
        Category selectedCategory = new CategoryModelDAO().loadCategoryById(categoryId);
        dao.getModelWrapper().getModel().setCategory(selectedCategory);
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryModelWrapper> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryModelWrapper> categoryList) {
        this.categoryList = categoryList;
    }

}
