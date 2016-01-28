/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util.hjpf.wrapper;

import java.lang.reflect.Field;

/**
 *
 * @author mohamed
 */
public abstract class AbstractModelWrapper<T> implements Cloneable{
   protected T model;
   
    public String getDelaredFieldsString(){
        String s = "";
     Field[] fs=  this.getClass().getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field field = fs[i];
            
            s+=field.getName();
            if(i<(fs.length-1)){
                s+=",";}
        }
     return s;
     
    }
    
   /* public AbstractModelWrapper(T model) {
        this.model = model;
    }*/
   
    public abstract T getModel() ;
    public abstract Integer getId() ;

    public void setModel(T model) {
        this.model = model;
    }
    

   

    @Override
    protected AbstractModelWrapper clone()   {
        AbstractModelWrapper w=null; 
        try{
        w =  (AbstractModelWrapper) super.clone();
        } catch(CloneNotSupportedException ex){
         ex.printStackTrace();
        }
     return null;
    }
    
    
}
