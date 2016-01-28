/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.prescriptionData;

import com.os.ks.work.concentration.ConcentrationModelDAO;
import com.os.ks.work.dose.*;
import com.os.ks.work.form.FormModelDAO;
import com.os.ks.work.medication.MedicationModelDAO;
import com.os.models.Concentration;
import com.os.models.Dose;
import com.os.models.Form;
import com.os.models.Medication;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author mohamed
 */
@ManagedBean(name = "prescriptionDataView")
@ViewScoped
public class PrescriptionDataView extends ViewAbstract<DoseModelDAO> {

    private String doseName;
    private String medicationName;
    private String concentrationName;
    private String formName;
    //////////////////////////////////////////////////
    private MedicationModelDAO medicationDao = new MedicationModelDAO();
    private ConcentrationModelDAO concentrationDao = new ConcentrationModelDAO();
    private FormModelDAO formDao = new FormModelDAO();
    /////////////////////////////////////////////////
    Dose selectedDose = new Dose();
    Form selectedForm = new Form();
    Concentration selectedConcentration = new Concentration();
    Medication selectedMedication = new Medication();
    /////////////////////////////////////////////////
    private List<Dose> doseList = new ArrayList<>();
    private List<Medication> medicationList = new ArrayList<>();
    private List<Concentration> concentrationList = new ArrayList<>();
    private List<Form> formList = new ArrayList<>();
    ////////////////////////////////////////////////
    private boolean updateDoseFlag;
    private boolean updateFormFlag;
    private boolean updateMedicationFlag;
    private boolean updateConcentrationFlag;
    ///////////////////////////////////////////////
    private ResourceBundle medicationRes;
    private ResourceBundle formRes;
    private ResourceBundle concentrationRes;
    //////////////////////////////////////////////
    private Integer deleteTypeFlag;

    public PrescriptionDataView() {
        super(new DoseModelDAO(), "com.os.ks.work.dose.messages.Dose");
        medicationRes = ResourceBundle.getBundle("com.os.ks.work.medication.messages.Medication");
        formRes = ResourceBundle.getBundle("com.os.ks.work.dose.messages.Form");
        concentrationRes = ResourceBundle.getBundle("com.os.ks.work.dose.messages.Concentration");
        clearDataForm();
    }

    private void loadTables() {
        doseList = dao.loadDoseList();
        formList = formDao.loadFormList();
        medicationList = medicationDao.loadMedicationList();
        concentrationList = concentrationDao.loadConcentrationList();
    }

    public void clearDataForm() {
        doseName = "";
        medicationName = "";
        concentrationName = "";
        formName = "";
        /////////////////////////////////////////////////
        medicationDao = new MedicationModelDAO();
        concentrationDao = new ConcentrationModelDAO();
        formDao = new FormModelDAO();
        /////////////////////////////////////////////////
        selectedDose = new Dose();
        selectedForm = new Form();
        selectedConcentration = new Concentration();
        selectedMedication = new Medication();
        /////////////////////////////////////////////////
        doseList = new ArrayList<>();
        medicationList = new ArrayList<>();
        concentrationList = new ArrayList<>();
        formList = new ArrayList<>();
        ////////////////////////////////////////////////
        updateDoseFlag = false;
        updateFormFlag = false;
        updateMedicationFlag = false;
        updateConcentrationFlag = false;
        ///////////////////////////////////////////////
        deleteTypeFlag = 0;
        loadTables();
    }

    public void loadObject(int type, Object obj) {
        // type : 1- dose  2- medication 3- form 4- concentration
        switch (type) {
            case 1: {
                updateDoseFlag = true;
                selectedDose = (Dose) obj;
                doseName = selectedDose.getDoseName();
                break;
            }
            case 2: {
                updateMedicationFlag = true;
                selectedMedication = (Medication) obj;
                medicationName = selectedMedication.getMedicationName();
                break;
            }
            case 3: {
                updateFormFlag = true;
                selectedForm = (Form) obj;
                formName = selectedForm.getFormName();
                break;
            }
            case 4: {
                updateConcentrationFlag = true;
                selectedConcentration = (Concentration) obj;
                concentrationName = selectedConcentration.getConcentrationName();
                break;
            }
        }
    }

    public void saveObject(int type) {
        // type : 1- dose  2- medication 3- form 4- concentration
        switch (type) {
            case 1: {
                if (!doseName.trim().isEmpty()) {
                    dao.getModelWrapper().getModel().setDoseName(doseName);
                    if (dao.save()) {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("saveSuccessfulMsg"));
                    } else {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("savefailMsg"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:doseName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", res.getString("doseNameReq")));
                }
                break;
            }
            case 2: {
                if (!medicationName.trim().isEmpty()) {
                    medicationDao.getModelWrapper().getModel().setMedicationName(medicationName);
                    if (medicationDao.save()) {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("saveSuccessfulMsg"));
                    } else {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("savefailMsg"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:medicationName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", medicationRes.getString("medicationNameReq")));
                }
                break;
            }
            case 3: {
                if (!formName.trim().isEmpty()) {
                    formDao.getModelWrapper().getModel().setFormName(formName);
                    if (formDao.save()) {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("saveSuccessfulMsg"));
                    } else {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("savefailMsg"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:formName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", formRes.getString("formNameReq")));
                }
                break;
            }
            case 4: {
                if (!concentrationName.trim().isEmpty()) {
                    concentrationDao.getModelWrapper().getModel().setConcentrationName(concentrationName);
                    if (concentrationDao.save()) {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("saveSuccessfulMsg"));
                    } else {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("savefailMsg"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:concentrationName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", concentrationRes.getString("ConcentrationNameReq")));
                }
                break;
            }
        }
        clearDataForm();
    }

    public void updateObject(int type) {
        // type : 1- dose  2- medication 3- form 4- concentration
        switch (type) {
            case 1: {
                if (!doseName.trim().isEmpty()) {
                    selectedDose.setDoseName(doseName);
                    dao.getModelWrapper().setModel(selectedDose);
                    if (dao.update()) {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("updateSuccessfulMsg"));
                    } else {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("savefailMsg"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:doseName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", res.getString("doseNameReq")));
                }
                break;
            }
            case 2: {
                if (!medicationName.trim().isEmpty()) {
                    selectedMedication.setMedicationName(medicationName);
                    medicationDao.getModelWrapper().setModel(selectedMedication);
                    if (medicationDao.update()) {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("updateSuccessfulMsg"));
                    } else {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("savefailMsg"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:medicationName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", medicationRes.getString("medicationNameReq")));
                }
                break;
            }
            case 3: {
                if (!formName.trim().isEmpty()) {
                    selectedForm.setFormName(formName);
                    formDao.getModelWrapper().setModel(selectedForm);
                    if (formDao.update()) {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("updateSuccessfulMsg"));
                    } else {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("savefailMsg"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:formName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", formRes.getString("formNameReq")));
                }
                break;
            }
            case 4: {
                if (!concentrationName.trim().isEmpty()) {
                    selectedConcentration.setConcentrationName(concentrationName);
                    concentrationDao.getModelWrapper().setModel(selectedConcentration);
                    if (concentrationDao.update()) {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("updateSuccessfulMsg"));
                    } else {
                        CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("savefailMsg"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:concentrationName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", concentrationRes.getString("concentrationNameReq")));
                }
                break;
            }
        }
        clearDataForm();
    }

    public void prepareObjectToDelete(Object obj, Integer type) {
        deleteTypeFlag = type;
        switch (type) {
            case 1: {
                selectedDose = (Dose) obj;
                break;
            }
            case 2: {
                selectedMedication = (Medication) obj;
                break;
            }
            case 3: {
                selectedForm = (Form) obj;
                break;
            }
            case 4: {
                selectedConcentration = (Concentration) obj;
                break;
            }
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('confirmation').show();");
    }

    public void deleteObject(int type) {
        // type : 1- dose  2- medication 3- form 4- concentration
        switch (type) {
            case 1: {
//                selectedDose = (Dose) obj;
                dao.getModelWrapper().setModel(selectedDose);
                if (dao.delete()) {
                    CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("deleteSuccessfulMsg"));
                } else {
                    CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("deletefailMsg"));
                }
                break;
            }
            case 2: {
//                selectedMedication = (Medication) obj;
                medicationDao.getModelWrapper().setModel(selectedMedication);
                if (medicationDao.delete()) {
                    CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("deleteSuccessfulMsg"));
                } else {
                    CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("deletefailMsg"));
                }
                break;
            }
            case 3: {
//                selectedForm = (Form) obj;
                formDao.getModelWrapper().setModel(selectedForm);
                if (formDao.delete()) {
                    CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("deleteSuccessfulMsg"));
                } else {
                    CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("deletefailMsg"));
                }
                break;
            }
            case 4: {
//                selectedConcentration = (Concentration) obj;
                concentrationDao.getModelWrapper().setModel(selectedConcentration);
                if (concentrationDao.delete()) {
                    CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_INFO, "INFO: ", commonRes.getString("deleteSuccessfulMsg"));
                } else {
                    CommonUtil.ViewGloabalMessage(FacesMessage.SEVERITY_ERROR, "Error: ", commonRes.getString("deletefailMsg"));
                }
                break;
            }
        }
        clearDataForm();
    }

    @Override
    public DoseModelDAO getDAO() {
        //  (DoseModelDAO) dao.getModelWrapper().
        return (DoseModelDAO) dao;
    }

    @Override
    public List<ColumnModel> getColumns() {
        List<ColumnModel> cols = super.getColumns();
        Map colsOptions = new HashMap();
        //  colsOptions.put(WIDTH, "5,20,20,20,15,15");
        colsOptions.put(STYLE, "hidden-phone,col2");
        reGenerateCols(cols, colsOptions);
        return cols;
    }

    private boolean validate() {
        return true;
    }

    @Override
    protected boolean validateSave() {

        return validate();
    }

    @Override
    protected boolean validateUpdate() {
        return validate();
    }

    public String getDoseName() {
        return doseName;
    }

    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getConcentrationName() {
        return concentrationName;
    }

    public void setConcentrationName(String concentrationName) {
        this.concentrationName = concentrationName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public List<Dose> getDoseList() {
        return doseList;
    }

    public void setDoseList(List<Dose> doseList) {
        this.doseList = doseList;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    public List<Concentration> getConcentrationList() {
        return concentrationList;
    }

    public void setConcentrationList(List<Concentration> concentrationList) {
        this.concentrationList = concentrationList;
    }

    public List<Form> getFormList() {
        return formList;
    }

    public void setFormList(List<Form> formList) {
        this.formList = formList;
    }

    public boolean isUpdateDoseFlag() {
        return updateDoseFlag;
    }

    public void setUpdateDoseFlag(boolean updateDoseFlag) {
        this.updateDoseFlag = updateDoseFlag;
    }

    public boolean isUpdateFormFlag() {
        return updateFormFlag;
    }

    public void setUpdateFormFlag(boolean updateFormFlag) {
        this.updateFormFlag = updateFormFlag;
    }

    public boolean isUpdateMedicationFlag() {
        return updateMedicationFlag;
    }

    public void setUpdateMedicationFlag(boolean updateMedicationFlag) {
        this.updateMedicationFlag = updateMedicationFlag;
    }

    public boolean isUpdateConcentrationFlag() {
        return updateConcentrationFlag;
    }

    public void setUpdateConcentrationFlag(boolean updateConcentrationFlag) {
        this.updateConcentrationFlag = updateConcentrationFlag;
    }

    public Dose getSelectedDose() {
        return selectedDose;
    }

    public void setSelectedDose(Dose selectedDose) {
        this.selectedDose = selectedDose;
    }

    public Form getSelectedForm() {
        return selectedForm;
    }

    public void setSelectedForm(Form selectedForm) {
        this.selectedForm = selectedForm;
    }

    public Concentration getSelectedConcentration() {
        return selectedConcentration;
    }

    public void setSelectedConcentration(Concentration selectedConcentration) {
        this.selectedConcentration = selectedConcentration;
    }

    public Medication getSelectedMedication() {
        return selectedMedication;
    }

    public void setSelectedMedication(Medication selectedMedication) {
        this.selectedMedication = selectedMedication;
    }

    public Integer getDeleteTypeFlag() {
        return deleteTypeFlag;
    }

    public void setDeleteTypeFlag(Integer deleteTypeFlag) {
        this.deleteTypeFlag = deleteTypeFlag;
    }

}
