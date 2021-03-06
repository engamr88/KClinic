package com.os.models;
// Generated Jan 12, 2016 5:08:18 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Medication generated by hbm2java
 */
public class Medication  implements java.io.Serializable {


     private Integer medicationId;
     private String medicationName;
     private Set<Prescription> prescriptions = new HashSet<Prescription>(0);

    public Medication() {
    }

	
    public Medication(String medicationName) {
        this.medicationName = medicationName;
    }
    public Medication(String medicationName, Set<Prescription> prescriptions) {
       this.medicationName = medicationName;
       this.prescriptions = prescriptions;
    }
   
    public Integer getMedicationId() {
        return this.medicationId;
    }
    
    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }
    public String getMedicationName() {
        return this.medicationName;
    }
    
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }
    public Set<Prescription> getPrescriptions() {
        return this.prescriptions;
    }
    
    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }




}


