/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.os.util.hjpf.view;

import com.os.util.hjpf.dao.AbstractDAOWrapper;

/**
 *
 * @author mohamed
 */
public class LookUpView  extends ViewAbstract<AbstractDAOWrapper>{
    private String titleBundleId;
    private boolean codeRequried = false;
    

    public LookUpView(AbstractDAOWrapper dao , String titleBundleId) {
        super(dao, "com.os.common.messages.Lookups");
        this.titleBundleId = titleBundleId;
        
    }
    

    
    @Override
    public AbstractDAOWrapper getDAO() {
        return super.dao;
    }

    @Override
    protected boolean validateSave() {
        return true;
    }

    @Override
    protected boolean validateUpdate() {
        return true;
    }

    @Override
    public String getViewTtile() {
        return res.getString(titleBundleId); 
    }

    public boolean isCodeRequried() {
        return codeRequried;
    }

    public void setCodeRequried(boolean codeRequried) {
        this.codeRequried = codeRequried;
    }

  
    
    
    
    
}
