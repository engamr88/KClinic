/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.session;

import com.os.models.Detection;
import com.os.models.Session;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class SessionModelDAO extends AbstractDAOWrapper<SessionModelWrapper> {

    public SessionModelDAO() {
        initModelWrapper();
        elementsMap.put("id", "model.sessionId");
        elementsMap.put("sessionName", "model.sessionName");
        elementsMap.put("categoryTitle", "model.category.categoryTitle");
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {
//(Integer id, String sessionName, Date sessionDate, Double amountPaid, String notes) {
        String HQL = "SELECT "
                + " new " + SessionModelWrapper.class.getName() + "("
                + " (model.sessionId) as id , "
                + " (model.sessionDate) as sessionDate,"
                + " (model.amountPaid) as amountPaid,"
                + " (category.notes) as notesSS "
                + ") "
                + " FROM Session model left join  model.detection detection";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.sessionId) "
                + " FROM Session model ";
        //  + " where model.sessionType in (1,4) group by model.sessionId";
//        extraCondition = " model.sessionType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Session model left join fetch model.detection detection where model.sessionId= " + id;
        return HQL;
    }

    public Session loadSessionById(Integer id) {
        String HQL = "FROM Session model left join fetch model.detection detection where model.sessionId= " + id;
        return (Session) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Session d = (Session) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public SessionModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        SessionModelWrapper sessionMW = new SessionModelWrapper();
        sessionMW.setModel(new Session());
        sessionMW.getModel().setDetection(new Detection());
        setModelWrapper(sessionMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
