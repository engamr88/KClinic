/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.patient;

import com.os.models.Patient;
import com.os.models.Quest;

/**
 *
 * @author amr
 */
public class PatientQuestAnswerWrapper {

    private Integer questAnswerId;
    private Patient patient;
    private Quest quest;
    private String answerString;
    private boolean answerBool;
    private String notes;

    public PatientQuestAnswerWrapper() {
      
    }

    public PatientQuestAnswerWrapper(Integer questAnswerId,Patient patient, Quest quest, String answerString,boolean answerBool, String notes) {
        this.questAnswerId=questAnswerId;
        this.patient = patient;
        this.quest = quest;
        this.answerString = answerString;
        this.answerBool=answerBool;
        this.notes = notes;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isAnswerBool() {
        return answerBool;
    }

    public void setAnswerBool(boolean answerBool) {
        this.answerBool = answerBool;
    }

    public Integer getQuestAnswerId() {
        return questAnswerId;
    }

    public void setQuestAnswerId(Integer questAnswerId) {
        this.questAnswerId = questAnswerId;
    }

}
