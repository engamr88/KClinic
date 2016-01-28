/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.concentration;

import com.os.ks.work.concentration.*;
import com.os.ks.work.concentration.*;
import com.os.ks.work.concentration.*;
import com.os.models.Concentration;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class ConcentrationModelWrapper extends AbstractModelWrapper<Concentration> {

    public ConcentrationModelWrapper() {
        initModel();
    }
    private Integer id;
    private String concentrationName;

    public ConcentrationModelWrapper(Integer id, String concentrationName) {
        this.id = id;
        this.concentrationName = concentrationName;
    }

    @Override
    public Concentration getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Concentration concentration = new Concentration();
        setModel(new Concentration());
    }

    public String getConcentrationName() {
        return concentrationName;
    }

    public void setConcentrationName(String concentrationName) {
        this.concentrationName = concentrationName;
    }

   
}
