package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Request;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */
@Repository("requestDao")
public class RequestDaoImpl extends AbstractDao<Integer,Request> implements RequestDao{

    @Override
    public List<Request> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("request_datetime"));
        List<Request> requestList = crit.list();
        if(requestList!=null){
            for(Request request:requestList){
              Hibernate.initialize(request.getCountry());
              Hibernate.initialize(request.getFrom_Cities());
              Hibernate.initialize(request.getHotel_Rating());
              Hibernate.initialize(request.getMeal_Type());
        }
        }
        return requestList;
    }

    @Override
    public Request findById(Integer id) {
        Request request = getByKey(id);
        if(request!=null){
              Hibernate.initialize(request.getCountry());
              Hibernate.initialize(request.getFrom_Cities());
              Hibernate.initialize(request.getHotel_Rating());
              Hibernate.initialize(request.getMeal_Type());  
        }
        return request;
    }

    @Override
    public List<Request> findByDate(Timestamp request_DateTime) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("request_datetime", request_DateTime));
        List<Request> requestList = crit.list();
        if(requestList!=null){
            for(Request request:requestList){
              Hibernate.initialize(request.getCountry());
              Hibernate.initialize(request.getFrom_Cities());
              Hibernate.initialize(request.getHotel_Rating());
              Hibernate.initialize(request.getMeal_Type());
            }
        }
        return requestList;
    }

    @Override
    public List<Request> findByDatesInterval(Timestamp dateFrom, Timestamp dateTill) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.le("request_datetime",dateTill));
        crit.add(Restrictions.ge("request_datetime",dateFrom));
        List<Request> requestList = crit.list();
        if(requestList!=null){
            for(Request request:requestList){
              Hibernate.initialize(request.getCountry());
              Hibernate.initialize(request.getFrom_Cities());
              Hibernate.initialize(request.getHotel_Rating());
              Hibernate.initialize(request.getMeal_Type());
            }
        } 
        return requestList;
        }
    
    
    
    @Override
    public void saveRequest(Request request) {
        persist(request);
    }

    @Override
    public void mergeRequest(Request request) {
        merge(request);
    }
    
    @Override
    public void deleteRequest(Request request) {
        delete(request);
    }
    
}
