/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.doctor;

import com.os.ks.work.user.UserModelWrapper;
import com.os.models.User;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class DoctorModelDAO extends AbstractDAOWrapper<UserModelWrapper> {

    public DoctorModelDAO() {
        initModelWrapper();
    }

    public UserModelWrapper fetchLoginedUser(String userName, String enctyptpassword) {
        String HQL = "SELECT "
                + " new " + UserModelWrapper.class.getName() + "("
                + " (model.userId) as id , "
                + " (model.userName) as userName,"
                + " (model.userPassword) as userPassword,"
                + " (model.userType) as userType "
                + ") "
                + " from User model where model.userName = '" + userName + "' and model.userPassword = '" + enctyptpassword + "' ";
        HQL += createSearchCrti(null, null, new HashMap());
        return (UserModelWrapper) uniqueResult(HQL);
    }

    public User loadDoctorById(Integer doctorId) {
        String HQL = " from User model where model.userId = " + doctorId;

        return (User) uniqueResult(HQL);
    }

    public List<DoctorModelWrapper> doctorWrapperList() {
        String hql = "SELECT "
                + " new " + DoctorModelWrapper.class.getName() + "("
                + " (model.userId) as id , "
                + " (model.userFullName) as userFullName, "
                + " (model.userMobile) as userMobile, "
                + " (model.userAddress) as userAddress "
                + ") "
                + " FROM User model where model.userType =2";
        return list(hql);
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {

        String HQL = "SELECT "
                + " new " + UserModelWrapper.class.getName() + "("
                + " (model.userId) as id , "
                + " (model.userFullName) as userFullName, "
                + " (model.userMobile) as userMobile, "
                + " (model.userAddress) as userAddress "
                + ") "
                + " FROM User model";
        extraCondition = " model.userType =2";

        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.userId) "
                + " FROM User model ";
        //  + " where model.userType in (1,4) group by model.userId";
//        extraCondition = " model.userType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM User model where model.userId= " + id;
        return HQL;
    }

    public User loadUserById(Integer id) {
        String HQL = "FROM User model where model.userId= " + id;
        return (User) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        User d = (User) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public UserModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        UserModelWrapper userMW = new UserModelWrapper();
        setModelWrapper(userMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
