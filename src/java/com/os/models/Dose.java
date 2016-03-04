package com.os.models;
// Generated Jan 12, 2016 5:08:18 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Dose generated by hbm2java
 */
public class Dose  implements java.io.Serializable {


     private Integer doseId;
     private String doseName;
     private Set<Prescription> prescriptions = new HashSet<Prescription>(0);

    public Dose() {
    }

	
    public Dose(String doseName) {
        this.doseName = doseName;
    }
    public Dose(String doseName, Set<Prescription> prescriptions) {
       this.doseName = doseName;
       this.prescriptions = prescriptions;
    }
   
    public Integer getDoseId() {
        return this.doseId;
    }
    
    public void setDoseId(Integer doseId) {
        this.doseId = doseId;
    }
    public String getDoseName() {
        return this.doseName;
    }
    
    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }
    public Set<Prescription> getPrescriptions() {
        return this.prescriptions;
    }
    
    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }




}

