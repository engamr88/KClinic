/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.reservation;

import com.os.ks.work.category.CategoryModelDAO;
import com.os.ks.work.category.CategoryModelWrapper;
import com.os.ks.work.doctor.DoctorModelDAO;
import com.os.ks.work.doctor.DoctorModelWrapper;
import com.os.ks.work.patient.PatientModelDAO;
import com.os.ks.work.patient.PatientModelWrapper;
import com.os.ks.work.priceList.PriceListModelDAO;
import com.os.models.Category;
import com.os.models.Patient;
import com.os.models.PriceList;
import com.os.models.Reservation;
import com.os.models.User;
import com.os.util.CommonListReslover;
import com.os.util.CommonUtil;
import com.os.util.HibernateUtil;
import com.os.util.hjpf.view.ColumnModel;
import com.os.util.hjpf.view.ViewAbstract;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mohamed
 */
@ManagedBean(name = "reservationView")
@SessionScoped
public class ReservationView extends ViewAbstract<ReservationModelDAO> {

    private Date cal;
    private List<Reservation> reservationList = new ArrayList<>();
    private List<PatientModelWrapper> patientList = new ArrayList<>();
    private PatientModelWrapper selectedPatient = new PatientModelWrapper();
    private List<PriceList> listOfPriceList = new ArrayList<>();
    List<PriceWrapper> priceWrapperList = new ArrayList<>();
    private Integer categoryId;
    private Integer doctorId;
    private Integer priceId;
    private List<DoctorModelWrapper> doctorList = new ArrayList<>();
    private List<CategoryModelWrapper> categoryList = new ArrayList<>();
    private Date today;
    private boolean showCalendarStyle;

    public ReservationView() {
        super(new ReservationModelDAO(), "com.os.ks.work.reservation.messages.Reservation");
        Calendar todayCal = Calendar.getInstance();
        todayCal.set(Calendar.HOUR, 0);
        todayCal.set(Calendar.AM_PM, Calendar.AM);
        todayCal.set(Calendar.MINUTE, 0);
        todayCal.set(Calendar.SECOND, 0);
        today = todayCal.getTime();
        System.out.println("Entered here 00000");
        reservationList = dao.loadReservationList(new Date());
        //patientList = new PatientModelDAO().loadPatientWrapperList();
        categoryId = -1;
        doctorId = -1;
        priceId = -1;
        doctorList = new DoctorModelDAO().doctorWrapperList();
        categoryList = new CategoryModelDAO().loadCategoryWrapperList();
        cal = new Date();
        showCalendarStyle = true;
    }

    public void changeStyle(boolean status) {
        showCalendarStyle = status;
        System.out.println("showCalendarStyle :: " + showCalendarStyle);
    }

    public void onCategoryLoad() {
        if (categoryId != -1) {
            listOfPriceList = new PriceListModelDAO().loadPriceListByCategoryId(categoryId);
            for (PriceList price : listOfPriceList) {
                priceWrapperList.add(new PriceWrapper(price.getPriceListId(), price.getPriceType(), price.getPriceAmount(), price.getPriceListId()));
            }
        }
    }

    @Override
    protected void clearForm() {
        super.clearForm(); //To change body of generated methods, choose Tools | Templates.
        categoryId = -1;
        reservationList = new ArrayList<>();
        selectedPatient = new PatientModelWrapper();
        listOfPriceList = new ArrayList<>();
        doctorId = -1;
        priceId = -1;
        priceWrapperList = new ArrayList<>();
        clearNewReservation();
    }

    private void loadReservations() {
        if (cal == null) {
            reservationList = dao.loadReservationList(new Date());
        } else {
            reservationList = dao.loadReservationList(cal);
        }
    }

    public void onDateReservations() {
        loadReservations();
        System.out.println("cal::" + cal);
        System.out.println("Today:: " + today);
    }

    @Override
    public ReservationModelDAO getDAO() {
        //  (ReservationModelDAO) dao.getModelWrapper().
        return (ReservationModelDAO) dao;
    }

    @Override
    public List<ColumnModel> getColumns() {
        List<ColumnModel> cols = super.getColumns();
        Map colsOptions = new HashMap();
        //  colsOptions.put(WIDTH, "5,20,20,20,15,15");
        colsOptions.put(STYLE, "hidden-phone,col2,col3,hidden,hidden-phone,hidden,hidden,hidden");
        reGenerateCols(cols, colsOptions);
        return cols;
    }

    @Override
    public void save() {
        if (validateSave()) {
            System.out.println("Entered here .......");
            super.save(); //To change body of generated methods, choose Tools | Templates.
            onDateReservations();
            clearNewReservation();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("window.location.href='../nurseIndex.xhtml'");
        }

    }

    @Override
    protected void beforeSave() {
        Patient selectedPatientModel = new PatientModelDAO().loadPatientById(selectedPatient.getId());
        Category selectedCategory = new CategoryModelDAO().loadCategoryById(categoryId);
        PriceList price = new PriceListModelDAO().loadPriceListById(priceId);
        User doctor = new DoctorModelDAO().loadDoctorById(doctorId);
        dao.getModelWrapper().getModel().setPatient(selectedPatientModel);
        dao.getModelWrapper().getModel().setCategory(selectedCategory);
        dao.getModelWrapper().getModel().setDoctor(doctor);
        dao.getModelWrapper().getModel().setPriceList(price);
        dao.getModelWrapper().getModel().setReservationDate(new Date());
        if (reservationList != null && reservationList.isEmpty()) {
            dao.getModelWrapper().getModel().setReservationNumber(1);
            dao.getModelWrapper().getModel().setCurrent(true);
        } else {
            int temp = 0;
            dao.getModelWrapper().getModel().setCurrent(false);
            if (reservationList != null) {
                for (Reservation reserv : reservationList) {
                    if (reserv.getDoctor().getUserId().equals(doctorId)) {
                        if (reserv.getReservationNumber() > temp) {
                            temp = reserv.getReservationNumber();
                        }
                    }
                }
            }
            dao.getModelWrapper().getModel().setReservationNumber(temp + 1);
        }
    }

    public ArrayList<PatientModelWrapper> completePlayerByName(String query) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria crit = session.createCriteria(Patient.class);
        crit.add(Restrictions.like("patientFullName", query + "%", MatchMode.ANYWHERE));
        crit.setMaxResults(5);
        List<Patient> results = crit.list();
        patientList = new ArrayList<>();
        for (Patient patient : results) {
            patientList.add(new PatientModelWrapper(patient.getPatientId(), patient.getPatientFullName(), patient.getPatientMobile(), patient.getPatientAddress()));
        }
        return (ArrayList<PatientModelWrapper>) patientList;
    }

    @Override
    public List getCommonList(int key) {
        if (key == CommonListReslover.PATIENT_LIST) {
            return patientList;
        }

        return null;
    }

    public void clearNewReservation() {
        doctorId = -1;
        categoryId = -1;
    }

    @Override
    protected boolean validateSave() {
        System.out.println("DoctorId :: " + doctorId);
        System.out.println("categoryId :: " + categoryId);
//        System.out.println("selectedPatient.getId() :: " + selectedPatient.getId());
        if (categoryId == -1) {
            System.out.println("Entered category select validation 0000000000000");
            CommonUtil.ViewValidationMessage("form:reservCategory", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        } else if (doctorId == -1) {
            CommonUtil.ViewValidationMessage("form:doctor", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        } else if (selectedPatient == null || selectedPatient.getId() == null) {
            CommonUtil.ViewValidationMessage("form:customPojo", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        } else if (priceId == -1 || priceId == null) {
            CommonUtil.ViewValidationMessage("form:price", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        } else if (dao.getModelWrapper().getModel().getReservationType() == null) {
            CommonUtil.ViewValidationMessage("form:reservType", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        }

        System.out.println("selectedPatient.getId():: " + selectedPatient.getId());
        System.out.println("reservationList:: " + reservationList);
        if (selectedPatient.getId() != null) {
            if (reservationList != null && !reservationList.isEmpty()) {
                System.out.println("Entered selecting patient valid");
                System.out.println("reservationList.size(): " + reservationList.size());
                for (Reservation reserv : reservationList) {
                    if (reserv.getPatient().getPatientId().equals(selectedPatient.getId())) {
                        CommonUtil.ViewValidationMessage("form:customPojo", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("alreadyReserved"));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void delayReservation(Reservation reservation) {
        int temp = reservation.getReservationNumber() + 1;
        for (Reservation reserv : reservationList) {
            if (reserv.getReservationNumber().equals(temp)) {
                reserv.setReservationNumber(temp - 1);
                dao.getModelWrapper().setModel(reserv);
                dao.update();
                break;
            }
        }
        reservation.setReservationNumber(temp);
        dao.getModelWrapper().setModel(reservation);
        dao.update();
        loadReservations();
    }

    public void cancelReservation(Reservation reservation) {
        if (reservationList.get(0).getReservationId().equals(reservation.getReservationId())) { // first of list
            dao.getModelWrapper().setModel(reservation);
            dao.delete();
            reservationList.remove(reservation);
            int temp = 1;
            for (Reservation reserv : reservationList) {
                reserv.setReservationNumber(temp);
                if (temp == 1) {
                    reserv.setCurrent(true);
                }
                dao.getModelWrapper().setModel(reserv);
                dao.update();
                temp++;
            }

        } else if (reservationList.get(reservationList.size() - 1).getReservationId().equals(reservation.getReservationId())) { // get last
            dao.getModelWrapper().setModel(reservation);
            dao.delete();
        } else {
            int temp = reservation.getReservationNumber();
            List<Reservation> restReservList = new ArrayList<>();
            for (Reservation reserv : reservationList) {
                if (reserv.getReservationNumber() > temp) {
                    restReservList.add(reserv);
                }
            }
            for (Reservation restReserv : restReservList) {
                restReserv.setReservationNumber(temp);
                dao.getModelWrapper().setModel(restReserv);
                dao.update();
                temp++;
            }
            dao.getModelWrapper().setModel(reservation);
            dao.delete();
        }
        loadReservations();
    }

    @Override
    protected boolean validateUpdate() {
        return true;
    }

    public Date getCal() {
        return cal;
    }

    public void setCal(Date cal) {
        this.cal = cal;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<PatientModelWrapper> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<PatientModelWrapper> patientList) {
        this.patientList = patientList;
    }

    public PatientModelWrapper getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(PatientModelWrapper selectedPatient) {
        this.selectedPatient = selectedPatient;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryModelWrapper> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryModelWrapper> categoryList) {
        this.categoryList = categoryList;
    }

    public List<PriceList> getListOfPriceList() {
        return listOfPriceList;
    }

    public void setListOfPriceList(List<PriceList> listOfPriceList) {
        this.listOfPriceList = listOfPriceList;
    }

    public List<PriceWrapper> getPriceWrapperList() {
        return priceWrapperList;
    }

    public void setPriceWrapperList(List<PriceWrapper> priceWrapperList) {
        this.priceWrapperList = priceWrapperList;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public List<DoctorModelWrapper> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<DoctorModelWrapper> doctorList) {
        this.doctorList = doctorList;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public boolean isShowCalendarStyle() {
        return showCalendarStyle;
    }

    public void setShowCalendarStyle(boolean showCalendarStyle) {
        this.showCalendarStyle = showCalendarStyle;
    }

}
