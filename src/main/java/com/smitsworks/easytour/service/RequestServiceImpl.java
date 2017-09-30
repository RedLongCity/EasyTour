package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.RequestDao;
import com.smitsworks.easytour.models.Request;
import java.util.List;
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
    public Request findByFields(Request request) {
        return requestDao.findRequestByFields(request);
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
            entity.setRequestDelay(request.getRequestDelay());
            requestDao.mergeRequest(entity);
        }
    }

    @Override
    public void saveOrUpdateRequest(Request request) {
        Request entity = requestDao.findById(request.getId());
        if(entity!=null){
            entity.setCountry(request.getCountry());
            entity.setFrom_Cities(request.getFrom_Cities());
            entity.setHotel_Rating(request.getHotel_Rating());
            entity.setMeal_Type(request.getMeal_Type());
            entity.setNight_From(request.getNight_From());
            entity.setNight_Till(request.getNight_Till());
            entity.setTourSet(request.getTourSet());
            entity.setRequestDelay(request.getRequestDelay());
            requestDao.mergeRequest(entity); 
        }else{
            requestDao.saveRequest(request);
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
    public void deleteAllRequests() {
        List<Request> requestList = requestDao.findAll();
        if(requestList!=null){
            for(Request request:requestList){
                requestDao.deleteRequest(request);
            }
        }
    }
}
