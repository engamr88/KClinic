/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.medication;

import com.os.models.Medication;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class MedicationModelDAO extends AbstractDAOWrapper<MedicationModelWrapper> {

    public MedicationModelDAO() {
        initModelWrapper();
    }

    public List<Medication> loadMedicationList() {
        String hql = " FROM Medication model";
        return list(hql);
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + MedicationModelWrapper.class.getName() + "("
                + " (model.medicationId) as id , "
                + " (model.medicationName) as medicationName "
                + ") "
                + " FROM Medication model";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.medicationId) "
                + " FROM Medication model ";
        //  + " where model.medicationType in (1,4) group by model.medicationId";
//        extraCondition = " model.medicationType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Medication model where model.medicationId= " + id;
        return HQL;
    }

    public Medication loadMedicationById(Integer id) {
        String HQL = "FROM Medication model where model.medicationId= " + id;
        return (Medication) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Medication d = (Medication) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public MedicationModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        MedicationModelWrapper medicationMW = new MedicationModelWrapper();
        setModelWrapper(medicationMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
