/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.RequestPullElementDao;
import com.smitsworks.easytour.models.RequestPullElement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for service operations with RequestPullElement
 */
@Service("requestPullElementService")
@Transactional
public class RequestPullElementServiceImpl implements RequestPullElementService {

    @Autowired
    RequestPullElementDao requestPullElementDao;
    
    @Override
    public RequestPullElement findById(Integer id) {
        return requestPullElementDao.findById(id);
    }

    @Override
    public void saveRequestPullElement(RequestPullElement requestPollElement) {
        requestPullElementDao.saveRequestPullElement(requestPollElement);
    }

    @Override
    public void updateRequestPullElement(RequestPullElement requestPullElement) {
        RequestPullElement entity = requestPullElementDao.findById(requestPullElement.getId());
        if(entity!=null){
            entity.setRequest(requestPullElement.getRequest());
            entity.setRequest_pull_DateTime(requestPullElement.getRequest_pull_DateTime());
            entity.setDone(requestPullElement.getDone());
            entity.setByHuman(requestPullElement.getByHuman());
            entity.setPriority(requestPullElement.getPriority());
            entity.setUpdateSession(requestPullElement.getUpdateSession());
            requestPullElementDao.mergeRequestPullElement(entity);
        }
    }

    @Override
    public void deleteRequestPullElement(RequestPullElement requestPullElement) {
        requestPullElementDao.deleteRequestPullElement(requestPullElement);
    }

    @Override
    public void deleteElementsBeforeDate(Timestamp date) {
        List<RequestPullElement> elementsList = requestPullElementDao.findBeforeDate(date);
        if(elementsList!=null){
            for(RequestPullElement element:elementsList){
                requestPullElementDao.deleteRequestPullElement(element);
            }
        }
    }

    @Override
    public void deleteElementsBetweenDates(Timestamp dateBefore, Timestamp dateTill) {
        List<RequestPullElement> elementsList = requestPullElementDao.
                findByDatesInterval(dateTill, dateTill);
        if(elementsList!=null){
            for(RequestPullElement element:elementsList){
                requestPullElementDao.deleteRequestPullElement(element);
            }
        }
    }
    
    

    @Override
    public List<RequestPullElement> findAll() {
        return requestPullElementDao.findAll();
    }

    @Override
    public List<RequestPullElement> findByData(Timestamp request_DateTime) {
        return requestPullElementDao.findByDate(request_DateTime);
    }

    @Override
    public List<RequestPullElement> findByDateInterval(Timestamp dateFrom, Timestamp dateTill) {
        return requestPullElementDao.findByDatesInterval(dateFrom, dateTill);
    }

    @Override
    public void deleteAllRequestPullElements() {
        List<RequestPullElement> requestPullElementList = requestPullElementDao.findAll();
        if(requestPullElementList!=null){
            for(RequestPullElement requestPullElement:requestPullElementList){
                requestPullElementDao.deleteRequestPullElement(requestPullElement);
            }
        }
    }
    
}
