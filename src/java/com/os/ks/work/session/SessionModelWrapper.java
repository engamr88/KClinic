/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.session;

import com.os.models.Detection;
import com.os.models.DetectionSession;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.util.Date;

/**
 *
 * @author egyptianeagle
 */
public class SessionModelWrapper extends AbstractModelWrapper<DetectionSession> {

    public SessionModelWrapper() {
        initModel();
    }
    private Integer id;
    private String sessionName;
    private Date sessionDate;
    private Double amountPaid;
    private String notes;

    public SessionModelWrapper(Integer id, String sessionName, Date sessionDate, Double amountPaid, String notes) {
        this.id = id;
        this.sessionName = sessionName;
        this.sessionDate = sessionDate;
        this.amountPaid = amountPaid;
        this.notes = notes;
    }

    @Override
    public DetectionSession getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        DetectionSession session = new DetectionSession();
        session.setDetection(new Detection());
        setModel(session);
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

   
}
