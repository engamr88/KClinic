/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Eng Amr
 */
@ManagedBean(name="validator")
@ViewScoped
public class Validator implements Serializable{
    
    private String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NAME_PATTERN = "[a-zA-z]+([ '.][ a-zA-Z]+)*";
    private static final String FileName_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@$%!]).{8,40})";
    public String getEmailPattern() {
        return emailPattern;
    }
    public static boolean isNameValid(String name) {
        boolean isValid = false;
        CharSequence inputStr = name.replaceAll("\\(", " ").replaceAll("\\)", " ").replaceAll("\\s+", " ").trim();
        //Make the comparison case-insensitive.
        Pattern pattern = Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    public static boolean isNameValidForFilemanger(String name) {
        boolean isValid = false;
        CharSequence inputStr = name.replaceAll("\\(", " ").replaceAll("\\)", " ").replaceAll("\\s+", " ").replaceAll("\\s+", " ").trim();
        //Make the comparison case-insensitive.
        Pattern pattern = Pattern.compile(FileName_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

}
