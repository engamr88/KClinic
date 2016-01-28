/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.ks.work.patient;

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
@FacesConverter(value = "patient")
public class patientConverter implements Converter{
      @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.trim().isEmpty() || value.equals("null")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                for (Object s : CommonUtil.getNavigator().getCurrentView().getCommonList(CommonListReslover.PATIENT_LIST)) {
                    if (((PatientModelWrapper) s).getId() == id) {
                        return s;
                    }
                }

            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "mmmmmmmmmmmmmmmmmmmmm";
        } else {
            return String.valueOf(((PatientModelWrapper) value).getId());
        }
    }
}
