/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.patient;

import com.os.ks.work.category.CategoryModelDAO;
import com.os.ks.work.category.CategoryModelWrapper;
import com.os.ks.work.quest.QuestModelDAO;
import com.os.ks.work.questAnswer.QuestAnswerModelDAO;
import com.os.ks.work.questAnswer.QuestAnswerModelWrapper;
import com.os.ks.work.reservation.ReservationView;
import com.os.ks.work.user.LoginModelView;
import com.os.models.Category;
import com.os.models.Patient;
import com.os.models.Quest;
import com.os.models.QuestAnswer;
import com.os.util.CommonUtil;
import com.os.util.hjpf.view.ColumnModel;
import com.os.util.hjpf.view.ViewAbstract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mohamed
 */
@ManagedBean(name = "patientView")
@ViewScoped
public class PatientView extends ViewAbstract<PatientModelDAO> {

    private Integer categoryId = -1;
    private List<CategoryModelWrapper> categoryList = new ArrayList<>();
    private List<PatientQuestAnswerWrapper> patientQuestAnsWrapperList = new ArrayList<>();
    private List<QuestAnswer> patientQuestAnswerList = new ArrayList<>();
    private static Patient selectedPatient = new Patient();

    public PatientView() {
        super(new PatientModelDAO(), "com.os.ks.work.patient.messages.Patient");
        setDatablesaveMergeVisiable(true);
        categoryList = new CategoryModelDAO().loadCategoryWrapperList();
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        ReservationView reservationView = (ReservationView) httpsession.getAttribute("reservationView");
        if (reservationView != null) {
            reservationView.setShowCalendarStyle(false);
        }
    }

    public void onCategorySelection() {
        if (categoryId != -1) {
            List<Quest> questList = new QuestModelDAO().loadAllCategoryQuests(categoryId);
            for (Quest quest : questList) {
                patientQuestAnsWrapperList.add(new PatientQuestAnswerWrapper(null, null, quest, null, false, null));
            }
        }
    }

    @Override
    public PatientModelDAO getDAO() {
        //  (PatientModelDAO) dao.getModelWrapper().
        return (PatientModelDAO) dao;
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
    protected void afterLoad() {
        selectedPatient = dao.getModelWrapper().getModel();
        System.out.println("patient afterLoad ::" + selectedPatient.getPatientId());
        patientQuestAnswerList = new QuestAnswerModelDAO().loadQuestAnswerByPatientId(dao.getModelWrapper().getModel().getPatientId());
        if (!patientQuestAnswerList.isEmpty()) {
            categoryId = patientQuestAnswerList.get(0).getQuest().getCategory().getCategoryId();
            patientQuestAnsWrapperList = new ArrayList<>();
            for (QuestAnswer questAnswer : patientQuestAnswerList) {
                if (questAnswer.getQuest().getQuestType().equals(1)) {
                    patientQuestAnsWrapperList.add(new PatientQuestAnswerWrapper(questAnswer.getQuestAnswerId(), questAnswer.getPatient(), questAnswer.getQuest(), null, Integer.parseInt(questAnswer.getQuestAnswerName()) == 1, questAnswer.getQuestAnswerNote()));
                } else {
                    patientQuestAnsWrapperList.add(new PatientQuestAnswerWrapper(questAnswer.getQuestAnswerId(), questAnswer.getPatient(), questAnswer.getQuest(), questAnswer.getQuestAnswerName(), false, questAnswer.getQuestAnswerNote()));
                }
            }
        }
    }

    @Override
    protected void clearForm() {
        super.clearForm(); //To change body of generated methods, choose Tools | Templates.
        categoryId = -1;
        patientQuestAnsWrapperList = new ArrayList<>();
        selectedPatient = new Patient();
    }

    private boolean validate() {
        ResourceBundle docRes = ResourceBundle.getBundle("com.os.ks.work.doctor.messages.Doctor");
        ResourceBundle questRes = ResourceBundle.getBundle("com.os.ks.work.quest.messages.Quest");
        if (dao.getModelWrapper().getModel().getPatientFullName().trim().isEmpty()) {
            CommonUtil.ViewValidationMessage("form:patientFullName", FacesMessage.SEVERITY_ERROR, "INFO: ", docRes.getString("enterInfo"));
            return false;
        }
        if (dao.getModelWrapper().getModel().getPatientMobile().trim().isEmpty()) {
            CommonUtil.ViewValidationMessage("form:patientMobile", FacesMessage.SEVERITY_ERROR, "INFO: ", docRes.getString("enterInfo"));
            return false;
        }
        if (categoryId == -1) {
            CommonUtil.ViewValidationMessage("form:category", FacesMessage.SEVERITY_ERROR, "INFO: ", questRes.getString("slcCategoryReq"));
            return false;
        }
        int i = 0;
        for (PatientQuestAnswerWrapper patientQuestAns : patientQuestAnsWrapperList) {
            if (patientQuestAns.getQuest().getQuestType().equals(2) && patientQuestAns.getAnswerString().trim().isEmpty()) {
                CommonUtil.ViewValidationMessage("form:answerWrapperList:" + i + ":questAnsString", FacesMessage.SEVERITY_ERROR, "INFO: ", docRes.getString("enterInfo"));
                return false;
            }
            i++;
        }
        return true;
    }

    @Override
    protected boolean validateSave() {
        return validate();
    }

    @Override
    public Object saveMerge() {
        if (validate()) {
            Patient patientModel = (Patient) super.saveMerge(); //To change body of generated methods, choose Tools | Templates.
            clearForm();
            System.out.println("patientModel.getPatientId():: " + patientModel.getPatientId());
            for (PatientQuestAnswerWrapper patientQuestAns : patientQuestAnsWrapperList) {
                QuestAnswerModelDAO questAnswerDao = new QuestAnswerModelDAO();
                questAnswerDao.setModelWrapper(new QuestAnswerModelWrapper());
                questAnswerDao.getModelWrapper().setModel(new QuestAnswer());
                questAnswerDao.getModelWrapper().getModel().setPatient(patientModel);
                questAnswerDao.getModelWrapper().getModel().setQuest(patientQuestAns.getQuest());
                questAnswerDao.getModelWrapper().getModel().setQuestAnswerNote(patientQuestAns.getNotes());
                if (patientQuestAns.getQuest().getQuestType().equals(1)) {
                    if (patientQuestAns.isAnswerBool()) {
                        questAnswerDao.getModelWrapper().getModel().setQuestAnswerName("1");
                    } else {
                        questAnswerDao.getModelWrapper().getModel().setQuestAnswerName("0");
                    }
                } else {
                    questAnswerDao.getModelWrapper().getModel().setQuestAnswerName(patientQuestAns.getAnswerString());
                }
                QuestAnswer questAnswerSaved = (QuestAnswer) questAnswerDao.saveMerge();
                patientQuestAns.setQuestAnswerId(questAnswerSaved.getQuestAnswerId());
            }
        }
        return null;
    }

    @Override
    public void update() {
        if (validateUpdate()) {
            if (dao.saveOrUpdate()) {
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("updateSuccessfulMsg"));
            } else {
                String failMessage;
                try {
                    failMessage = res.getString("savefailMsg");
                } catch (Exception e) {
                    failMessage = commonRes.getString("savefailMsg");
                }
                CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", failMessage);
            }
        }
        QuestAnswerModelDAO questAnswerDao;
        System.out.println("patientId::" + selectedPatient.getPatientId());
        for (QuestAnswer questAnswer : patientQuestAnswerList) {
            for (PatientQuestAnswerWrapper patientQuestAns : patientQuestAnsWrapperList) {
                if (questAnswer.getQuestAnswerId().equals(patientQuestAns.getQuestAnswerId())) {
                    questAnswerDao = new QuestAnswerModelDAO();
                    questAnswer.setPatient(selectedPatient);
                    questAnswer.setQuest(patientQuestAns.getQuest());
                    questAnswer.setQuestAnswerNote(patientQuestAns.getNotes());
                    if (patientQuestAns.getQuest().getQuestType().equals(1)) {
                        if (patientQuestAns.isAnswerBool()) {
                            questAnswer.setQuestAnswerName("1");
                        } else {
                            questAnswer.setQuestAnswerName("0");
                        }
                    } else {
                        questAnswer.setQuestAnswerName(patientQuestAns.getAnswerString());
                    }
                    questAnswerDao.setModelWrapper(new QuestAnswerModelWrapper());
                    questAnswerDao.getModelWrapper().setModel(questAnswer);
                    questAnswerDao.update();
                }
            }
        }
        clearForm();
    }

    @Override
    public void delete() {
        dao.loadModelWrapper();
        patientQuestAnswerList = new QuestAnswerModelDAO().loadQuestAnswerByPatientId(dao.getModelWrapper().getModel().getPatientId());
        QuestAnswerModelDAO questAnswerDao;
        for (QuestAnswer questAnswer : patientQuestAnswerList) {
            questAnswerDao = new QuestAnswerModelDAO();
            questAnswerDao.setModelWrapper(new QuestAnswerModelWrapper());
            questAnswerDao.getModelWrapper().setModel(questAnswer);
            questAnswerDao.delete();
        }
        if (dao.delete()) {
            clearForm();
            CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "INFO: ", commonRes.getString("deleteSuccessfulMsg"));
        } else {
            String failMessage;
            try {
                failMessage = res.getString("deletefailMsg");
            } catch (Exception e) {
                failMessage = commonRes.getString("deletefailMsg");
            }
            CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", failMessage);

        }
    }

    @Override
    protected boolean validateUpdate() {
        return validate();
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

    public List<PatientQuestAnswerWrapper> getPatientQuestAnsWrapperList() {
        return patientQuestAnsWrapperList;
    }

    public void setPatientQuestAnsWrapperList(List<PatientQuestAnswerWrapper> patientQuestAnsWrapperList) {
        this.patientQuestAnsWrapperList = patientQuestAnsWrapperList;
    }

}
