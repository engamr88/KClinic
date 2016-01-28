/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.questAnswer;

import com.os.ks.work.questAnswer.*;
import com.os.models.Dose;
import com.os.models.Medication;
import com.os.models.Patient;
import com.os.models.Quest;
import com.os.models.QuestAnswer;
import com.os.util.hjpf.wrapper.AbstractModelWrapper;
import java.util.Date;

/**
 *
 * @author egyptianeagle
 */
public class QuestAnswerModelWrapper extends AbstractModelWrapper<QuestAnswer> {

    public QuestAnswerModelWrapper() {
        initModel();
    }
    private Integer id;
    private String questName;
    private String patientFullName;
    private String questAnswerName;
    private String questAnswerNote;

    public QuestAnswerModelWrapper(Integer id, String questName, String patientFullName, String questAnswerName, String questAnswerNote) {
        this.id = id;
        this.questName = questName;
        this.patientFullName = patientFullName;
        this.questAnswerName = questAnswerName;
        this.questAnswerNote = questAnswerNote;
    }

    @Override
    public QuestAnswer getModel() {
        return model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    private void initModel() {
        QuestAnswer questAnswer = new QuestAnswer();
        questAnswer.setQuest(new Quest());
        questAnswer.setPatient(new Patient());
        setModel(questAnswer);
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getQuestAnswerName() {
        return questAnswerName;
    }

    public void setQuestAnswerName(String questAnswerName) {
        this.questAnswerName = questAnswerName;
    }

    public String getQuestAnswerNote() {
        return questAnswerNote;
    }

    public void setQuestAnswerNote(String questAnswerNote) {
        this.questAnswerNote = questAnswerNote;
    }

}
