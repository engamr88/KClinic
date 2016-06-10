/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.reservation;

import com.os.models.Category;
import com.os.models.Patient;
import com.os.models.PriceList;
import com.os.models.Reservation;
import com.os.models.User;
import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author egyptianeagle
 */
public class ReservationModelDAO extends AbstractDAOWrapper<ReservationModelWrapper> {

    public ReservationModelDAO() {
        initModelWrapper();
    }

    @Override
    protected String createListHQL(String orderBy, String orderMode, Map filters) {
//(Integer id, String categoryTitle, String patientFullName, Integer reservationType, Date reservationDate, Integer reservationNumber, Double detectionPrice, Double consultPrice) {
        String HQL = "SELECT "
                + " new " + ReservationModelWrapper.class.getName() + "("
                + " (model.reservationId) as id , "
                + " (category.categoryTitle) as categoryTitle, "
                + " (patient.patientFullName) as patientFullName, "
                + " (model.reservationType) as reservationType, "
                + " (model.reservationDate) as reservationDate, "
                + " (model.reservationNumber) as reservationNumber, "
                + " (priceList.priceType) as priceType, "
                + " (priceList.priceAmount) as priceAmount, "
                + " (doctor.userFullName) as doctorFullName "
                + ") "
                + " FROM Reservation model "
                + "left join model.category category "
                + "left join model.patient patient "
                + "left join model.priceList priceList "
                + "left join model.doctor user";
        HQL += createSearchCrti(orderBy, orderMode, filters);
        return HQL;
    }

    @Override
    protected String createCountHQL(String orderBy, String orderMode, Map filters) {
        String HQL = "SELECT "
                + " count(model.reservationId) "
                + " FROM Reservation model ";
        //  + " where model.reservationType in (1,4) group by model.reservationId";
//        extraCondition = " model.reservationType =1";
        HQL += createSearchCrti(null, null, filters);
        return HQL;
    }

    public List<Reservation> loadReservationList(Date date) {
        System.out.println("Date before :: " + date);
        Calendar now = Calendar.getInstance();
        System.out.println("now.get(Calendar.HOUR):: " + now.get(Calendar.HOUR));
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calAfter.setTime(date);
        calBefore.setTime(date);

        calBefore.set(Calendar.HOUR, 7);
        calBefore.set(Calendar.AM_PM, Calendar.AM);
        calBefore.set(Calendar.MINUTE, 0);
        calBefore.set(Calendar.SECOND, 0);
        if (now.get(Calendar.HOUR) >= 0 && now.get(Calendar.HOUR) < 7 && now.get(Calendar.AM_PM) == Calendar.AM) {
            calBefore.add(Calendar.DAY_OF_MONTH, -1);
        }
        Date startDate = calBefore.getTime();

        calAfter.set(Calendar.HOUR, 3);
        calAfter.set(Calendar.MINUTE, 0);
        calAfter.set(Calendar.SECOND, 0);
        calAfter.set(Calendar.AM_PM, Calendar.AM);
        if (now.get(Calendar.HOUR) >= 0 && now.get(Calendar.HOUR) < 7 && now.get(Calendar.AM_PM) == Calendar.AM) {

        } else {
            calAfter.add(Calendar.DAY_OF_MONTH, 1);
        }
        Date endDate = calAfter.getTime();

        String startDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate);
        String endDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
        String hql = "FROM Reservation model "
                + "left join fetch model.category category "
                + "left join fetch model.patient patient "
                + "left join fetch model.priceList priceList "
                + "left join fetch model.doctor doctor where model.archive=0 and "
                + "(model.reservationDate >= '" + startDateString + "' and model.reservationDate <='" + endDateString + "')"
                + " order by model.reservationNumber";
        return list(hql);
    }

    public List<Reservation> loadReservationList(Date date, Integer doctorId) {
        System.out.println("Date before :: " + date);
        Calendar now = Calendar.getInstance();
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calAfter.setTime(date);
        calBefore.setTime(date);

        calBefore.set(Calendar.HOUR, 7);
        calBefore.set(Calendar.AM_PM, Calendar.AM);
        calBefore.set(Calendar.MINUTE, 0);
        calBefore.set(Calendar.SECOND, 0);
        if (now.get(Calendar.HOUR) >= 0 && now.get(Calendar.HOUR) < 7 && now.get(Calendar.AM_PM) == Calendar.AM) {
            calBefore.add(Calendar.DAY_OF_MONTH, -1);
        }
        Date startDate = calBefore.getTime();

        calAfter.set(Calendar.HOUR, 3);
        calAfter.set(Calendar.MINUTE, 0);
        calAfter.set(Calendar.SECOND, 0);
        calAfter.set(Calendar.AM_PM, Calendar.AM);
        if (now.get(Calendar.HOUR) >= 0 && now.get(Calendar.HOUR) < 7 && now.get(Calendar.AM_PM) == Calendar.AM) {

        } else {
            calAfter.add(Calendar.DAY_OF_MONTH, 1);
        }
//        }
        Date endDate = calAfter.getTime();

        String startDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate);
        String endDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
        String hql = "FROM Reservation model "
                + "left join fetch model.category category "
                + "left join fetch model.patient patient "
                + "left join fetch model.priceList priceList "
                + "left join fetch model.doctor doctor where model.archive=0 and "
                + "(model.reservationDate >= '" + startDateString + "' and model.reservationDate <='" + endDateString + "') "
                + "and doctor.userId=" + doctorId
                + " order by model.reservationNumber";
        return list(hql);
    }

    @Override
    protected String createLoadHQL(Integer id) {
        String HQL = "FROM Reservation model "
                + "left join fetch model.category category "
                + "left join fetch model.patient patient "
                + "left join fetch model.priceList priceList "
                + "left join fetch model.doctor doctor"
                + " where model.reservationId= " + id;
        return HQL;
    }

    public Reservation loadReservationById(Integer id) {
        String HQL = "FROM Reservation model "
                + "left join fetch model.category category "
                + "left join fetch model.patient patient "
                + "left join fetch model.priceList priceList "
                + "left join fetch model.doctor doctor"
                + " where model.reservationId= " + id;
        return (Reservation) uniqueResult(HQL);
    }

    @Override
    public boolean checkDuplication(String idName, Integer idvalue, String fieldName, String fieldValue, String extraCondition, String modelClass) {
        return super.checkDuplication(idName, idvalue, fieldName, fieldValue, extraCondition, modelClass); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModelWrapper() {
        Reservation d = (Reservation) load(getModelWrapper().getId());
        getModelWrapper().setModel(d);
    }

    @Override
    public ReservationModelWrapper getModelWrapper() {
        return super.getModelWrapper();
    }

    private void initModelWrapper() {
        ReservationModelWrapper reservationMW = new ReservationModelWrapper();
        reservationMW.setModel(new Reservation());
        reservationMW.getModel().setCategory(new Category());
        reservationMW.getModel().setPatient(new Patient());
        reservationMW.getModel().setPriceList(new PriceList());
        reservationMW.getModel().setDoctor(new User());
        setModelWrapper(reservationMW);
    }

    @Override
    public void clear() {
        initModelWrapper();
    }
}
