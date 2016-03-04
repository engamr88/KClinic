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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
    private PatientModelWrapper selectedPatient = new PatientModelWrapper();
    private List<PriceList> listOfPriceList = new ArrayList<>();
    List<PriceWrapper> priceWrapperList = new ArrayList<>();
    private Integer categoryId;
    private Integer doctorId;
    private Integer priceId;
    private List<DoctorModelWrapper> doctorList = new ArrayList<>();
    private List<CategoryModelWrapper> categoryList = new ArrayList<>();
    private Date reservedDate;
    public ReservationView() {
        super(new ReservationModelDAO(), "com.os.ks.work.reservation.messages.Reservation");
        try {
            String dataParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cal");
            if (dataParam != null) {
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy hh:mm a");
                SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                reservedDate = df.parse(dataParam);
            }
            reservationList = dao.loadReservationList(new Date());
            patientList = new PatientModelDAO().loadPatientWrapperList();
            categoryId = -1;
            doctorId = -1;
            priceId = -1;
            doctorList = new DoctorModelDAO().doctorWrapperList();
            categoryList = new CategoryModelDAO().loadCategoryWrapperList();
            cal = new Date();
        } catch (ParseException ex) {
            Logger.getLogger(ReservationView.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    }

    public void onDateReservations() {
        if (cal == null) {
            reservationList = dao.loadReservationList(new Date());
        } else {
            reservationList = dao.loadReservationList(cal);
        }
        System.out.println("cal::" + cal);
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

    @Override
    public void save() {
        super.save(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Entered here .......");
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
        dao.getModelWrapper().getModel().setReservationDate(reservedDate);
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
        System.out.println("DoctorId :: " + doctorId);
        if (doctorId == -1) {
            CommonUtil.ViewValidationMessage("form:doctor", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        } else if (priceId == -1 || priceId == null) {
            CommonUtil.ViewValidationMessage("form:price", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        } else if (dao.getModelWrapper().getModel().getReservationType() == null) {
            CommonUtil.ViewValidationMessage("form:reservType", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        } else if (dao.getModelWrapper().getModel().getReservationNumber() == null) {
            CommonUtil.ViewValidationMessage("form:reservType", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            return false;
        }
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

}
