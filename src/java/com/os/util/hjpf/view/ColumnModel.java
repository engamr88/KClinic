/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util.hjpf.view;

import java.io.Serializable;

/**
 *
 * @author mohamed
 */
  public class ColumnModel implements Serializable {

        private String header;
        private String property;
        private String width;
        private String style;  

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
        
        
        
    }
