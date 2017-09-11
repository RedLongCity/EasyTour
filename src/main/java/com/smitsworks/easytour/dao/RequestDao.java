package com.smitsworks.easytour.dao;

import java.util.List;
import com.smitsworks.easytour.models.Request;
import java.sql.Timestamp;
/**
 *
 * @author redlongcity
 */

public interface RequestDao {
    
    List<Request> findAll();
    
    Request findById(Integer id);
    
    Request findRequestByFields(Request request);
    
    List<Request> findByDate(Timestamp request_DateTime);
    
    List<Request> findByDatesInterval(Timestamp dateFrom,
            Timestamp dateTill);
    
    void saveRequest(Request request);
    
    void mergeRequest(Request request);
    
    void deleteRequest(Request request);
    
}
