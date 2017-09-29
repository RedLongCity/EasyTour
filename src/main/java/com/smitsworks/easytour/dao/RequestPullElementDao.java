package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.RequestPullElement;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * interface for RequestPull Dao manipulates
 */
public interface RequestPullElementDao {
    
   List<RequestPullElement> findAll();
   
   List<RequestPullElement> findByDate(Timestamp request_DateTime);
   
   List<RequestPullElement> findByDatesInterval(Timestamp dateFrom,
            Timestamp dateTill);
   
   List<RequestPullElement> findBeforeDate(Timestamp date);
   
   RequestPullElement findById(Integer id);
   
   void saveRequestPullElement(RequestPullElement requestPullElement);
   
   void mergeRequestPullElement(RequestPullElement requestPullElement);
   
   void deleteRequestPullElement(RequestPullElement requestPullElement);
    
}
