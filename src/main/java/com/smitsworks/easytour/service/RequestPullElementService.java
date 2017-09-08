/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.RequestPullElement;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * interface for Service operations with RequestPullElement
 */
public interface RequestPullElementService {
    
    RequestPullElement findById(Integer id);
    
    void saveRequestPullElement(RequestPullElement requestPollElement);
    
    void updateRequestPullElement(RequestPullElement requestPullElement);
    
    void deleteRequestPullElement(RequestPullElement requestPullElement);
    
    List<RequestPullElement> findAll();
    
    List<RequestPullElement> findByData(Timestamp request_DateTime);
    
    List<RequestPullElement> findByDateInterval(Timestamp dateFrom,
        Timestamp dateTill);
    
    void deleteAllRequestPullElements();
    
}
