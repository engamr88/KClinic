/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.medication;

import com.os.ks.work.medication.*;
import com.os.ks.work.medication.*;
import com.os.models.Medication;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class MedicationModelWrapper extends AbstractModelWrapper<Medication> {

    public MedicationModelWrapper() {
        initModel();
    }
    private Integer id;
    private String medicationName;

    public MedicationModelWrapper(Integer id, String medicationName) {
        this.id = id;
        this.medicationName = medicationName;
    }

    @Override
    public Medication getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        Medication medication = new Medication();
        setModel(new Medication());
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

   
}
