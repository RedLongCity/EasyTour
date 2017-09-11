package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.Request;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author redlongcity
 */

public interface RequestService {

Request findById(Integer id);
    
void saveRequest(Request request);

void updateRequest(Request request);

void saveOrUpdateRequest(Request request);

void deleteRequest(Request request);

List<Request> findAll();

List<Request> findByDate(Timestamp request_DateTime);

List<Request> findByDateInterval(Timestamp dateFrom,
        Timestamp dateTill);

void deleteAllRequests();

}
