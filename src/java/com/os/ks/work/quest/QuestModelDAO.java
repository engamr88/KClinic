/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.quest;

import com.os.models.Category;
import com.os.models.Quest;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class QuestModelDAO extends AbstractDAOWrapper<QuestModelWrapper> {

    public QuestModelDAO() {
        initModelWrapper();
        elementsMap.put("id", "model.questId");
        elementsMap.put("questName", "model.questName");
        elementsMap.put("categoryTitle", "model.category.categoryTitle");
    }
public List<Quest> loadAllCategoryQuests(int categoryId){
    String hql="FROM Quest model left join fetch model.category category";
    return list(hql);
}
    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + QuestModelWrapper.class.getName() + "("
                + " (model.questId) as id , "
                + " (model.questName) as questName,"
                + " (category.categoryTitle) as categoryTitle "
                + ") "
                + " FROM Quest model left join  model.category category";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.questId) "
                + " FROM Quest model ";
        //  + " where model.questType in (1,4) group by model.questId";
//        extraCondition = " model.questType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Quest model left join fetch model.category category where model.questId= " + id;
        return HQL;
    }

    public Quest loadQuestById(Integer id) {
        String HQL = "FROM Quest model left join fetch model.category category where model.questId= " + id;
        return (Quest) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Quest d = (Quest) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public QuestModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        QuestModelWrapper questMW = new QuestModelWrapper();
        questMW.setModel(new Quest());
        questMW.getModel().setCategory(new Category());
        setModelWrapper(questMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
