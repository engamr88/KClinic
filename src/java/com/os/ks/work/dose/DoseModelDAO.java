/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.dose;

import com.os.ks.work.dose.*;
import com.os.ks.work.dose.*;
import com.os.models.Dose;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class DoseModelDAO extends AbstractDAOWrapper<DoseModelWrapper> {

    public DoseModelDAO() {
        initModelWrapper();
    }

    public List<Dose> loadDoseList() {
        String hql = " FROM Dose model";
        return list(hql);
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + DoseModelWrapper.class.getName() + "("
                + " (model.doseId) as id , "
                + " (model.doseName) as doseName "
                + ") "
                + " FROM Dose model";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.doseId) "
                + " FROM Dose model ";
        //  + " where model.doseType in (1,4) group by model.doseId";
//        extraCondition = " model.doseType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Dose model where model.doseId= " + id;
        return HQL;
    }

    public Dose loadDoseById(Integer id) {
        String HQL = "FROM Dose model where model.doseId= " + id;
        return (Dose) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Dose d = (Dose) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public DoseModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        DoseModelWrapper doseMW = new DoseModelWrapper();
        setModelWrapper(doseMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
