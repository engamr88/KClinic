/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.os.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author egyptianeagle
 */
public class CommonSturctureRoles {
    private int dataKey;
    private int dataId;

    public CommonSturctureRoles(int dataKey,int dataId) {
        this.dataKey = dataKey;
        this.dataId=dataId;
    }

    public int getDataKey() {
        return dataKey;
    }

    public void setDataKey(int dataKey) {
        this.dataKey = dataKey;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }
    
    
    
}
