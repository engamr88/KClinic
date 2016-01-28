/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.dose;

import com.os.ks.work.dose.*;
import com.os.models.Dose;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class DoseModelWrapper extends AbstractModelWrapper<Dose> {

    public DoseModelWrapper() {
        initModel();
    }
    private Integer id;
    private String doseName;

    public DoseModelWrapper(Integer id, String doseName) {
        this.id = id;
        this.doseName = doseName;
    }

    @Override
    public Dose getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Dose dose = new Dose();
        setModel(new Dose());
    }

    public String getDoseName() {
        return doseName;
    }

    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }

   
}
