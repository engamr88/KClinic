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
    private Integer detectionId;
    private Integer caseStudeyId;

    public DetectionStudyModelWrapper(Integer id, String caseStudyName) {
        this.id = id;
        this.caseStudyName = caseStudyName;
    }

    public DetectionStudyModelWrapper(Integer id,Integer caseStudeyId, String caseStudyName) {
        this.id = id;
        this.caseStudyName = caseStudyName;
        this.caseStudeyId = caseStudeyId;
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

    public Integer getDetectionId() {
        return detectionId;
    }

    public void setDetectionId(Integer detectionId) {
        this.detectionId = detectionId;
    }

    public Integer getCaseStudeyId() {
        return caseStudeyId;
    }

    public void setCaseStudeyId(Integer caseStudeyId) {
        this.caseStudeyId = caseStudeyId;
    }

}
