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
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class QuestAnswerModelDAO extends AbstractDAOWrapper<QuestAnswerModelWrapper> {

    public QuestAnswerModelDAO() {
        initModelWrapper();
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {
//public QuestAnswerModelWrapper(Integer id, String doseName, String medicationName, String patientFullName, Date questAnswerDate) {
        String HQL = "SELECT "
                + " new " + QuestAnswerModelWrapper.class.getName() + "("
                + " (model.questAnswerId) as id , "
                + " (quest.questName) as questName, "
                + " (patient.patientFullName) as patientFullName, "
                + " (model.questAnswerName) as questAnswerName, "
                + " (model.questAnswerNote) as questAnswerNote "
                + ") "
                + " FROM QuestAnswer model"
                + " left join fetch model.quest quest"
                + " left join fetch model.patient patient";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    public List<QuestAnswer> loadQuestAnswerByPatientId(int patientId) {
        String hql = " FROM QuestAnswer model"
                + " left join fetch model.quest quest"
                + " left join fetch quest.category category"
                + " left join fetch model.patient patient where patient.patientId="+patientId;
        return list(hql);
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.questAnswerId) "
                + " FROM QuestAnswer model ";
        //  + " where model.questAnswerType in (1,4) group by model.questAnswerId";
//        extraCondition = " model.questAnswerType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM QuestAnswer model"
                + " FROM QuestAnswer model"
                + " left join fetch model.quest quest"
                + " left join fetch model.patient patient"
                + " where model.questAnswerId= " + id;
        return HQL;
    }

    public QuestAnswer loadQuestAnswerById(Integer id) {
        String HQL = "FROM QuestAnswer model"
                + " FROM QuestAnswer model"
                + " left join fetch model.quest quest"
                + " left join fetch model.patient patient"
                + " where model.questAnswerId= " + id;
        return (QuestAnswer) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        QuestAnswer d = (QuestAnswer) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public QuestAnswerModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        QuestAnswerModelWrapper questAnswerMW = new QuestAnswerModelWrapper();
        questAnswerMW.setModel(new QuestAnswer());
        questAnswerMW.getModel().setQuest(new Quest());
        questAnswerMW.getModel().setPatient(new Patient());
        setModelWrapper(questAnswerMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
