/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.concentration;

import com.os.models.Concentration;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class ConcentrationModelDAO extends AbstractDAOWrapper<ConcentrationModelWrapper> {

    public ConcentrationModelDAO() {
        initModelWrapper();
    }

     public List<Concentration> loadConcentrationList() {
        String hql = " FROM Concentration model";
        return list(hql);
    }
    
    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + ConcentrationModelWrapper.class.getName() + "("
                + " (model.concentrationId) as id , "
                + " (model.concentrationName) as concentrationName "
                + ") "
                + " FROM Concentration model";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.concentrationId) "
                + " FROM Concentration model ";
        //  + " where model.concentrationType in (1,4) group by model.concentrationId";
//        extraCondition = " model.concentrationType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Concentration model where model.concentrationId= " + id;
        return HQL;
    }

    public Concentration loadConcentrationById(Integer id) {
        String HQL = "FROM Concentration model where model.concentrationId= " + id;
        return (Concentration) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Concentration d = (Concentration) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public ConcentrationModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        ConcentrationModelWrapper concentrationMW = new ConcentrationModelWrapper();
        setModelWrapper(concentrationMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
