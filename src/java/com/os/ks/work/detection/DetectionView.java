/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.detection;

import com.os.ks.work.caseStudy.CaseStudyModelDAO;
import com.os.ks.work.caseStudy.CaseStudyModelWrapper;
import com.os.ks.work.detectionStudy.DetectionStudyModelDAO;
import com.os.ks.work.detectionStudy.DetectionStudyModelWrapper;
import com.os.ks.work.questAnswer.QuestAnswerModelDAO;
import com.os.ks.work.reservation.ReservationModelDAO;
import com.os.ks.work.session.SessionModelDAO;
import com.os.ks.work.session.SessionModelWrapper;
import com.os.models.CaseStudy;
import com.os.models.Detection;
import com.os.models.QuestAnswer;
import com.os.models.Reservation;
import com.os.models.DetectionSession;
import com.os.models.DetectionStudy;
import com.os.models.User;
import com.os.util.CommonListReslover;
import com.os.util.CommonUtil;
import com.os.util.HibernateUtil;
import com.os.util.hjpf.view.ColumnModel;
import com.os.util.hjpf.view.ViewAbstract;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mohamed
 */
@ManagedBean(name = "detectionView")
@ViewScoped
public class DetectionView extends ViewAbstract<DetectionModelDAO> {

    private List<Reservation> reservationList = new ArrayList<>();
    private Reservation currentReservation = new Reservation();
    private List<QuestAnswer> questAnswersList = new ArrayList<>();
    private List<CaseStudyModelWrapper> caseStudyList = new ArrayList<>();
    private CaseStudyModelWrapper selectedCaseStudy = new CaseStudyModelWrapper();
    private List<CaseStudyModelWrapper> patientCases = new ArrayList<>();
    private boolean activeCloseDetection;
    private boolean editActiveDetection;
    private Detection selectedDiagnose = new Detection();
    private List<DetectionSession> detectionSessionList = new ArrayList<>();
    private User loggedInDoctor = new User();
    private Double extraFees = 0d;
    private String extrafeesNotes = "";
    private List<DetectionStudyModelWrapper> detectionStudyList = new ArrayList<>();
    private DetectionSession detectionSession = new DetectionSession();

    public DetectionView() {
        super(new DetectionModelDAO(), "com.os.ks.work.detection.messages.Detection");
        loggedInDoctor = CommonUtil.fetchLogginedUser();
        reservationList = new ReservationModelDAO().loadReservationList(new Date(), loggedInDoctor.getUserId());
        if (!reservationList.isEmpty()) {
            for (Reservation reserv : reservationList) {
                if (reserv.isCurrent()) {
                    currentReservation = reserv;
                }
            }
        }
        if (currentReservation.getReservationId() != null) {
            questAnswersList = new QuestAnswerModelDAO().loadQuestAnswerByPatientId(currentReservation.getPatient().getPatientId());
        }
        selectedDiagnose = dao.checkDetectionCreatedFromReservation(currentReservation.getReservationId());
        if (selectedDiagnose == null) {
            activeCloseDetection = false;
            editActiveDetection = false;
        } else {
            activeCloseDetection = true;
            editActiveDetection = false;
            detectionSessionList = new SessionModelDAO().fetchDetectionSessions(selectedDiagnose.getDetectionId());
        }
    }

    @Override
    public DetectionModelDAO getDAO() {
        //  (DetectionModelDAO) dao.getModelWrapper().
        return (DetectionModelDAO) dao;
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

    public ArrayList<CaseStudyModelWrapper> completePlayerByName(String query) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria crit = session.createCriteria(CaseStudy.class);
        crit.add(Restrictions.like("caseStudyName", query + "%", MatchMode.ANYWHERE));
        crit.setMaxResults(5);
        List<CaseStudy> results = crit.list();
        caseStudyList = new ArrayList<>();
        for (CaseStudy caseStudy : results) {
            caseStudyList.add(new CaseStudyModelWrapper(caseStudy.getCaseStudyId(), caseStudy.getCaseStudyName()));
        }
        return (ArrayList<CaseStudyModelWrapper>) caseStudyList;
    }

    @Override
    public List getCommonList(int key) {
        if (key == CommonListReslover.CASE_STUDY_LIST) {
            return caseStudyList;
        }

        return null;
    }

    public void onAutoCompleteItemSelect() {
        if (selectedCaseStudy != null) {
            boolean found = false;
            for (CaseStudyModelWrapper caseWrapper : patientCases) {
                if (selectedCaseStudy.getId().equals(caseWrapper.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                patientCases.add(selectedCaseStudy);
            } else {
                CommonUtil.ViewValidationMessage("form:caseStudyId", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("duplicationError"));
            }
        }
    }

    public void createDiagnose() {
        if (!patientCases.isEmpty()) {
            activeCloseDetection = true;
            selectedDiagnose = new Detection();
            selectedDiagnose.setArchive(false);
            selectedDiagnose.setDoctor(loggedInDoctor);
            selectedDiagnose.setExtraFees(extraFees);
            selectedDiagnose.setNote(extrafeesNotes);
            selectedDiagnose.setPatient(currentReservation.getPatient());
            dao.getModelWrapper().setModel(selectedDiagnose);
            selectedDiagnose = (Detection) dao.saveMerge();
            for (CaseStudyModelWrapper caseWrapper : patientCases) {
                DetectionStudyModelDAO detectionStudyDao = new DetectionStudyModelDAO();
                CaseStudy caseStudy = new CaseStudyModelDAO().loadCaseStudyById(caseWrapper.getId());
                detectionStudyDao.setModelWrapper(new DetectionStudyModelWrapper());
                detectionStudyDao.getModelWrapper().setModel(new DetectionStudy());
                detectionStudyDao.getModelWrapper().getModel().setCaseStudy(caseStudy);
                detectionStudyDao.getModelWrapper().getModel().setDetection(selectedDiagnose);
                detectionStudyDao.save();
            }
            clear();
        } else {
            CommonUtil.ViewValidationMessage("form:caseStudyId", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("caseStudyReq"));
        }
    }

    public void editDiagnose() {
        editActiveDetection = true;
        patientCases = new ArrayList<>();
        detectionStudyList = new DetectionStudyModelDAO().detectionStudyList(selectedDiagnose.getDetectionId());
        for (DetectionStudyModelWrapper wrapper : detectionStudyList) {
            patientCases.add(new CaseStudyModelWrapper(wrapper.getCaseStudeyId(), wrapper.getCaseStudyName()));
        }
        extraFees = selectedDiagnose.getExtraFees();
        extrafeesNotes = selectedDiagnose.getNote();
    }

    public void updateDiagnose() {
        editActiveDetection = false;
        selectedDiagnose.setExtraFees(extraFees);
        selectedDiagnose.setNote(extrafeesNotes);
        dao.getModelWrapper().setModel(selectedDiagnose);
        dao.update();
        List<CaseStudyModelWrapper> removedCaseStudies = new ArrayList<>();
        for (CaseStudyModelWrapper caseWrapper : patientCases) {
            for (DetectionStudyModelWrapper wrapper : detectionStudyList) {
                if (caseWrapper.getId().equals(wrapper.getCaseStudeyId())) {
                    removedCaseStudies.add(caseWrapper);
                }
            }
        }
        patientCases.removeAll(removedCaseStudies);
        for (CaseStudyModelWrapper caseWrapper : patientCases) {
            DetectionStudyModelDAO detectionStudyDao = new DetectionStudyModelDAO();
            CaseStudy caseStudy = new CaseStudyModelDAO().loadCaseStudyById(caseWrapper.getId());
            detectionStudyDao.setModelWrapper(new DetectionStudyModelWrapper());
            detectionStudyDao.getModelWrapper().setModel(new DetectionStudy());
            detectionStudyDao.getModelWrapper().getModel().setCaseStudy(caseStudy);
            detectionStudyDao.getModelWrapper().getModel().setDetection(selectedDiagnose);
            detectionStudyDao.save();
        }
        clear();
    }

    public void closeDiagnose() {
        if (selectedDiagnose != null && selectedDiagnose.getDetectionId() != null) {
            if (selectedDiagnose.getExtraFees() != 0 && !detectionSessionList.isEmpty()) {
                double price = 0d;
                for (DetectionSession detectionSession : detectionSessionList) {
                    price += detectionSession.getAmountPaid();
                }
                if (price < selectedDiagnose.getExtraFees() || price > selectedDiagnose.getExtraFees()) {

                } else {

                }
            }
        }

    }

    private boolean validateSession() {
        boolean flag = true;
        double totalPaid = 0d;
        if (detectionSession.getDetectionSessionName().trim().isEmpty()) {
            CommonUtil.ViewValidationMessage("form:sessionName", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            flag = false;
        } else if (detectionSession.getAmountPaid().equals(0d)) {
            CommonUtil.ViewValidationMessage("form:amountPaid", FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("dataReq"));
            flag = false;
        } else if (detectionSession.getAmountPaid() > 0) {

            for (DetectionSession dsession : detectionSessionList) {
                totalPaid += dsession.getAmountPaid();
            }
            totalPaid += detectionSession.getAmountPaid();
            if (totalPaid > selectedDiagnose.getExtraFees()) {
                CommonUtil.ViewValidationMessage("form:amountPaid", FacesMessage.SEVERITY_ERROR, "INFO: ", res.getString("limitExceded"));
                flag = false;
            }

        }
        return flag;
    }

    public void addSession() {
        if (validateSession()) {
            detectionSession.setDetection(selectedDiagnose);
            detectionSession.setDetectionSessionDate(new Date());
            SessionModelDAO sessionDao = new SessionModelDAO();
            sessionDao.setModelWrapper(new SessionModelWrapper());
            sessionDao.getModelWrapper().setModel(detectionSession);
            sessionDao.save();
            detectionSessionList = new SessionModelDAO().fetchDetectionSessions(selectedDiagnose.getDetectionId());
        }
    }

    public void reservationDone() {
        activeCloseDetection = false;
        editActiveDetection = false;
    }

    @Override
    public void clear() {
        super.clear(); //To change body of generated methods, choose Tools | Templates.
        patientCases = new ArrayList<>();
        selectedCaseStudy = new CaseStudyModelWrapper();
        extraFees = 0d;
        extrafeesNotes = "";
    }

    @Override
    protected void afterCopy() {
    }

    @Override
    protected void afterLoad() {

    }

    @Override
    protected boolean validateSave() {

        return true;
    }

    @Override
    protected boolean validateUpdate() {
        return true;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public Reservation getCurrentReservation() {
        return currentReservation;
    }

    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
    }

    public List<QuestAnswer> getQuestAnswersList() {
        return questAnswersList;
    }

    public void setQuestAnswersList(List<QuestAnswer> questAnswersList) {
        this.questAnswersList = questAnswersList;
    }

    public List<CaseStudyModelWrapper> getCaseStudyList() {
        return caseStudyList;
    }

    public void setCaseStudyList(List<CaseStudyModelWrapper> caseStudyList) {
        this.caseStudyList = caseStudyList;
    }

    public CaseStudyModelWrapper getSelectedCaseStudy() {
        return selectedCaseStudy;
    }

    public void setSelectedCaseStudy(CaseStudyModelWrapper selectedCaseStudy) {
        this.selectedCaseStudy = selectedCaseStudy;
    }

    public List<CaseStudyModelWrapper> getPatientCases() {
        return patientCases;
    }

    public void setPatientCases(List<CaseStudyModelWrapper> patientCases) {
        this.patientCases = patientCases;
    }

    public boolean isActiveCloseDetection() {
        return activeCloseDetection;
    }

    public void setActiveCloseDetection(boolean activeCloseDetection) {
        this.activeCloseDetection = activeCloseDetection;
    }

    public Detection getSelectedDiagnose() {
        return selectedDiagnose;
    }

    public void setSelectedDiagnose(Detection selectedDiagnose) {
        this.selectedDiagnose = selectedDiagnose;
    }

    public List<DetectionSession> getDetectionSessionList() {
        return detectionSessionList;
    }

    public void setDetectionSessionList(List<DetectionSession> detectionSessionList) {
        this.detectionSessionList = detectionSessionList;
    }

    public Double getExtraFees() {
        return extraFees;
    }

    public void setExtraFees(Double extraFees) {
        this.extraFees = extraFees;
    }

    public String getExtrafeesNotes() {
        return extrafeesNotes;
    }

    public void setExtrafeesNotes(String extrafeesNotes) {
        this.extrafeesNotes = extrafeesNotes;
    }

    public boolean isEditActiveDetection() {
        return editActiveDetection;
    }

    public void setEditActiveDetection(boolean editActiveDetection) {
        this.editActiveDetection = editActiveDetection;
    }

    public DetectionSession getDetectionSession() {
        return detectionSession;
    }

    public void setDetectionSession(DetectionSession detectionSession) {
        this.detectionSession = detectionSession;
    }

}
