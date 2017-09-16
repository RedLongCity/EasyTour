package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.From_Cities;
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
        crit.addOrder(Order.asc("id"));
        List<Request> requestList = crit.list();
        if(requestList!=null){
            for(Request request:requestList){
              Hibernate.initialize(request.getCountry());
              Hibernate.initialize(request.getFrom_Cities());
              Hibernate.initialize(request.getHotel_Rating());
              Hibernate.initialize(request.getMeal_Type());
              Hibernate.initialize(request.getTourSet());
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
              Hibernate.initialize(request.getTourSet());

        }
        return request;
    }

    @Override
    public Request findRequestByFields(Request request) {
        Criteria crit = createCriteria();
        if(request.getCountry()!=null){
        crit.add(Restrictions.eq("country", 
                request.getCountry().getId()));
        }
        if(request.getFrom_Cities()!=null){
        crit.add(Restrictions.eq("from_Cities", 
                request.getFrom_Cities().getId()));
        }
        crit.add(Restrictions.eq("hotel_Rating",
                request.getHotel_Rating()));
        crit.add(Restrictions.eq("night_From", 
                request.getNight_From()));
        crit.add(Restrictions.eq("night_Till", 
                request.getNight_Till()));
        if(request.getMeal_Type()!=null){
        crit.add(Restrictions.eq("meal_Type", 
                request.getMeal_Type().getId()));
        }
        Request entity = (Request) crit.uniqueResult();
        if(entity!=null){
              Hibernate.initialize(entity.getCountry());
              Hibernate.initialize(entity.getFrom_Cities());
              Hibernate.initialize(entity.getHotel_Rating());
              Hibernate.initialize(entity.getMeal_Type());
              Hibernate.initialize(entity.getTourSet()); 
        }
        return entity;
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
