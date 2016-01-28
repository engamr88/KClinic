/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.reservation;

import com.os.ks.work.category.CategoryModelDAO;
import com.os.ks.work.category.CategoryModelWrapper;
import com.os.ks.work.patient.PatientModelDAO;
import com.os.ks.work.patient.PatientModelWrapper;
import com.os.models.Category;
import com.os.models.Patient;
import com.os.models.Reservation;
import com.os.util.CommonListReslover;
import com.os.util.HibernateUtil;
import com.os.util.hjpf.view.ColumnModel;
import com.os.util.hjpf.view.ViewAbstract;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@ViewScoped
public class ReservationView extends ViewAbstract<ReservationModelDAO> {

    private Date cal;
    private List<Reservation> reservationList = new ArrayList<>();
    private List<PatientModelWrapper> patientList = new ArrayList<>();
    private PatientModelWrapper selectedPatient = new PatientModelWrapper(); // load system services
    private Integer categoryId;
    private List<CategoryModelWrapper> categoryList = new ArrayList<>();
    public ReservationView() {
        super(new ReservationModelDAO(), "com.os.ks.work.reservation.messages.Reservation");
         reservationList = dao.loadReservationList(new Date());
        patientList = new PatientModelDAO().loadPatientWrapperList();
        categoryId=-1;
        categoryList=new CategoryModelDAO().loadCategoryWrapperList();
        cal=new Date();
    }

    @Override
    protected void clearForm() {
        super.clearForm(); //To change body of generated methods, choose Tools | Templates.
        categoryId=-1;
    }
    
    public void onDateReservations() {
        if (cal == null) {
            reservationList = dao.loadReservationList(new Date());
        } else {
            reservationList = dao.loadReservationList(cal);
        }
        System.out.println("cal::"+cal);
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("PF('reservationDlg').show();");
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

    public ArrayList<PatientModelWrapper> completePlayerByName(String query) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria crit = session.createCriteria(Patient.class);
        crit.add(Restrictions.like("patientFullName", query + "%", MatchMode.ANYWHERE));
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

    @Override
    protected boolean validateSave() {

        return true;
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

}
