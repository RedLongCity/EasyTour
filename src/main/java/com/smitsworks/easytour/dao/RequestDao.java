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
    
    void saveRequest(Request request);
    
    void mergeRequest(Request request);
    
    void deleteRequest(Request request);
    
}
