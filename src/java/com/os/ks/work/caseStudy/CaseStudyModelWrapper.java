/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.caseStudy;

import com.os.models.CaseStudy;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;

/**
 *
 * @author egyptianeagle
 */
public class CaseStudyModelWrapper extends AbstractModelWrapper<CaseStudy> {

    public CaseStudyModelWrapper() {
        initModel();
    }
    private Integer id;
    private String caseStudyName;

    public CaseStudyModelWrapper(Integer id, String caseStudyName) {
        this.id = id;
        this.caseStudyName = caseStudyName;
    }

    @Override
    public CaseStudy getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        CaseStudy caseStudy = new CaseStudy();
        setModel(new CaseStudy());
    }

    public String getCaseStudyName() {
        return caseStudyName;
    }

    public void setCaseStudyName(String caseStudyName) {
        this.caseStudyName = caseStudyName;
    }

   
}
