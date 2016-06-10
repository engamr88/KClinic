/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util;

import com.os.models.Patient;
import com.os.models.Quest;
import com.os.models.QuestAnswer;
import com.os.models.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mora_
 */
public class FillDB {

    private Session session;
    private Transaction t;

    private void loadNurse() {
        // 1- admin  2-Doctor  3- nurse 
        beginTransaction();
        for (int i = 1; i < 5; i++) {
            User nurse = new User();
            nurse.setUserType(3);
            nurse.setUserFullName("nurse-" + i);
            nurse.setUserName("nurse-" + i);
             try {
                nurse.setUserPassword(CommonUtil.encrypt("1234"));
            } catch (Exception ex) {
                Logger.getLogger(FillDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            nurse.setUserMobile("123456789");
            session.save(nurse);
        }
        t.commit();
        session.close();
    }

    private void loadDoctor() {
        // 1- admin  2-Doctor  3- nurse 
        beginTransaction();
        for (int i = 1; i < 5; i++) {
            User doctor = new User();
            doctor.setUserType(2);
            doctor.setUserFullName("doc-" + i);
            doctor.setUserName("doc-" + i);
            try {
                doctor.setUserPassword(CommonUtil.encrypt("1234"));
            } catch (Exception ex) {
                Logger.getLogger(FillDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            doctor.setUserMobile("123456789");
            session.save(doctor);
        }
        t.commit();
        session.close();
    }

    private void loadPatients() {
        beginTransaction();
        for (int i = 1; i < 1000; i++) {
            Patient patient = new Patient();
            patient.setPatientFullName("patient-" + i);
            patient.setPatientMobile("12345988458");
            patient = (Patient) session.merge(patient);
            String query = "from Quest q";
            List<Quest> questList = session.createQuery(query).list();
            for (Quest quest : questList) {
                QuestAnswer questAnswer = new QuestAnswer();
                questAnswer.setPatient(patient);
                questAnswer.setQuest(quest);
                questAnswer.setQuestAnswerName("1");
                session.save(questAnswer);
            }
        }
        t.commit();
        session.close();
    }

    private void beginTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        t = session.beginTransaction();
    }

    public static void main(String args[]) {
        new FillDB().loadPatients();
    }
}
