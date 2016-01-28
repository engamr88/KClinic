/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.form;

import com.os.ks.work.form.*;
import com.os.ks.work.form.*;
import com.os.models.Form;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class FormModelWrapper extends AbstractModelWrapper<Form> {

    public FormModelWrapper() {
        initModel();
    }
    private Integer id;
    private String formName;

    public FormModelWrapper(Integer id, String formName) {
        this.id = id;
        this.formName = formName;
    }

    @Override
    public Form getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Form form = new Form();
        setModel(new Form());
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

   
}
