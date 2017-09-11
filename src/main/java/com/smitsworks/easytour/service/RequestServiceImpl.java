package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.RequestDao;
import com.smitsworks.easytour.models.Request;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("requestService")
@Transactional
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestDao requestDao;
    
    @Override
    public Request findById(Integer id) {
        return requestDao.findById(id);
    }

    @Override
    public void saveRequest(Request request) {
        requestDao.saveRequest(request);
    }

    @Override
    public void updateRequest(Request request) {
        Request entity = requestDao.findById(request.getId());
        if(entity!=null){
            entity.setCountry(request.getCountry());
            entity.setFrom_Cities(request.getFrom_Cities());
            entity.setHotel_Rating(request.getHotel_Rating());
            entity.setMeal_Type(request.getMeal_Type());
            entity.setNight_From(request.getNight_From());
            entity.setNight_Till(request.getNight_Till());
            entity.setTourSet(request.getTourSet());
            requestDao.mergeRequest(entity);
        }
    }

    @Override
    public void deleteRequest(Request request) {
        requestDao.deleteRequest(request);
    }

    @Override
    public List<Request> findAll() {
        return requestDao.findAll();
    }

    @Override
    public List<Request> findByDate(Timestamp request_DateTime) {
        return requestDao.findByDate(request_DateTime);
    }

    @Override
    public List<Request> findByDateInterval(Timestamp dateFrom, Timestamp dateTill) {
        return requestDao.findByDatesInterval(dateFrom, dateTill);
    }

    @Override
    public void deleteAllRequests() {
        List<Request> requestList = requestDao.findAll();
        if(requestList!=null){
            for(Request request:requestList){
                requestDao.deleteRequest(request);
            }
        }
    }
    
}
