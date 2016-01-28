/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util.hjpf.view;

import com.os.util.hjpf.dao.AbstractDAOWrapper;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author mohamed
 */
public class LazyAbstractDataModel<T> extends LazyDataModel<T> {

    private AbstractDAOWrapper dao;
    //  List<AbstractModelWrapper> listData;

    public LazyAbstractDataModel(AbstractDAOWrapper dao) {
        this.dao = dao;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        String order = "DESC";
        if (sortOrder.equals(SortOrder.ASCENDING))
            order="ASC";
        
       Long count = dao.fetchCount(sortField, order, filters);
      
     this.setRowCount(count.intValue());  
     List listData = dao.list( first, pageSize , sortField, order, filters);
     return (List<T>) listData;
    } 

}
