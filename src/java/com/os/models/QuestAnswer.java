package com.os.models;
// Generated Jan 12, 2016 5:08:18 PM by Hibernate Tools 4.3.1



/**
 * QuestAnswer generated by hbm2java
 */
public class QuestAnswer  implements java.io.Serializable {


     private Integer questAnswerId;
     private Patient patient;
     private Quest quest;
     private String questAnswerName;
     private String questAnswerNote;

    public QuestAnswer() {
    }

	
    public QuestAnswer(Patient patient, Quest quest, String questAnswerName) {
        this.patient = patient;
        this.quest = quest;
        this.questAnswerName = questAnswerName;
    }
    public QuestAnswer(Patient patient, Quest quest, String questAnswerName, String questAnswerNote) {
       this.patient = patient;
       this.quest = quest;
       this.questAnswerName = questAnswerName;
       this.questAnswerNote = questAnswerNote;
    }
   
    public Integer getQuestAnswerId() {
        return this.questAnswerId;
    }
    
    public void setQuestAnswerId(Integer questAnswerId) {
        this.questAnswerId = questAnswerId;
    }
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Quest getQuest() {
        return this.quest;
    }
    
    public void setQuest(Quest quest) {
        this.quest = quest;
    }
    public String getQuestAnswerName() {
        return this.questAnswerName;
    }
    
    public void setQuestAnswerName(String questAnswerName) {
        this.questAnswerName = questAnswerName;
    }
    public String getQuestAnswerNote() {
        return this.questAnswerNote;
    }
    
    public void setQuestAnswerNote(String questAnswerNote) {
        this.questAnswerNote = questAnswerNote;
    }




}


