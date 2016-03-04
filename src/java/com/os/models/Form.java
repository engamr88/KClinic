package com.os.models;
// Generated Jan 12, 2016 5:08:18 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Form generated by hbm2java
 */
public class Form  implements java.io.Serializable {


     private Integer formId;
     private String formName;
     private Set<Prescription> prescriptions = new HashSet<Prescription>(0);

    public Form() {
    }

	
    public Form(String formName) {
        this.formName = formName;
    }
    public Form(String formName, Set<Prescription> prescriptions) {
       this.formName = formName;
       this.prescriptions = prescriptions;
    }
   
    public Integer getFormId() {
        return this.formId;
    }
    
    public void setFormId(Integer formId) {
        this.formId = formId;
    }
    public String getFormName() {
        return this.formName;
    }
    
    public void setFormName(String formName) {
        this.formName = formName;
    }
    public Set<Prescription> getPrescriptions() {
        return this.prescriptions;
    }
    
    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }




}

