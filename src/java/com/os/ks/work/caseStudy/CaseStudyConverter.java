/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.caseStudy;

import com.os.models.CaseStudy;
import com.os.util.CommonListReslover;
import com.os.util.CommonUtil;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author amr
 */
@FacesConverter(value = "caseStudy")
public class CaseStudyConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.trim().isEmpty() || value.equals("null")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                for (Object s : CommonUtil.getNavigator().getCurrentView().getCommonList(CommonListReslover.CASE_STUDY_LIST)) {
                    if (((CaseStudyModelWrapper) s).getId() == id) {
                        return s;
                    }
                }

            } catch (NumberFormatException exception) {
                //throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
                CaseStudyModelWrapper caseWrapper = null;
                CaseStudyModelDAO caseStudyDao = new CaseStudyModelDAO();
                CaseStudy caseStudyCheck = caseStudyDao.loadCaseStudyByName(value);
                if (caseStudyCheck == null || caseStudyCheck.getCaseStudyId() == null) {
                    caseStudyDao.setModelWrapper(new CaseStudyModelWrapper());
                    caseStudyDao.getModelWrapper().setModel(new CaseStudy());
                    caseStudyDao.getModelWrapper().getModel().setCaseStudyName(value);
                    CaseStudy caseStudy = (CaseStudy) caseStudyDao.saveMerge();
                    caseWrapper = new CaseStudyModelWrapper(caseStudy.getCaseStudyId(), caseStudy.getCaseStudyName());
                } else {
                    caseWrapper = new CaseStudyModelWrapper(caseStudyCheck.getCaseStudyId(), caseStudyCheck.getCaseStudyName());
                }
                return caseWrapper;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "mmmmmmmmmmmmmmmmmmmmm";
        } else {
            return String.valueOf(((CaseStudyModelWrapper) value).getId());
        }
    }
}
