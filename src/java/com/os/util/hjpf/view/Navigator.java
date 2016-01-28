/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util.hjpf.view;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author mohamed
 */
@ManagedBean(name="navigator")
@ViewScoped
public class Navigator  implements Serializable{
    private ViewAbstract currentView ;
    private String start = "";

    public Navigator() {
        System.out.println("CREATE NEW NAVIIIII ");
    }
    

    public ViewAbstract getCurrentView() {
        return currentView;
    }

    public void setCurrentView(ViewAbstract currentView) {
        this.currentView = currentView;
    }
    
    
  

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

   
    
    
    
    
}
