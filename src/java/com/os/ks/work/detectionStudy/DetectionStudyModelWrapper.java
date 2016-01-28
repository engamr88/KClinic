/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.detectionStudy;


import com.os.models.CaseStudy;
import com.os.models.Detection;
import com.os.models.DetectionStudy;
import com.os.models.Patient;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class DetectionStudyModelWrapper extends AbstractModelWrapper<DetectionStudy> {

    public DetectionStudyModelWrapper() {
        initModel();
    }
    private Integer id;
     private String caseStudyName;

    public DetectionStudyModelWrapper(Integer id, String caseStudyName) {
        this.id = id;
        this.caseStudyName = caseStudyName;
    }

    @Override
    public DetectionStudy getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        DetectionStudy detectionStudy = new DetectionStudy();
        detectionStudy.setCaseStudy(new CaseStudy());
        detectionStudy.setDetection(new Detection());
        detectionStudy.getDetection().setPatient(new Patient());
        setModel(detectionStudy);
    }

    public String getCaseStudyName() {
        return caseStudyName;
    }

    public void setCaseStudyName(String caseStudyName) {
        this.caseStudyName = caseStudyName;
    }

   
   
}
