package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.From_Cities;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */
@Repository("from_CitiesDao")
public class From_CitiesDaoImpl extends AbstractDao<String,From_Cities> implements From_CitiesDao {

    @Override
    public List<From_Cities> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<From_Cities> from_CitiesList = (List<From_Cities>)crit.list();
        if(from_CitiesList!=null){
        for(From_Cities from_Cities: from_CitiesList){
            Hibernate.initialize(from_Cities.getCountrySet());
            Hibernate.initialize(from_Cities.getTours());
            Hibernate.initialize(from_Cities.getRequests());
        }
        }
        return from_CitiesList;
    }

    @Override
    public From_Cities findById(String id) {
        From_Cities from_Cities = getByKey(id);
        if(from_Cities!=null){
            Hibernate.initialize(from_Cities.getCountrySet());
            Hibernate.initialize(from_Cities.getTours());
            Hibernate.initialize(from_Cities.getRequests());

        }
        return from_Cities;
    }

    @Override
    public void save(From_Cities from_Cities) {
        persist(from_Cities);
    }

    @Override
    public void deleteFrom_Cities(From_Cities from_Cities) {
        delete(from_Cities);
    }


    @Override
    public void mergeFrom_Cities(From_Cities from_Cities) {
        merge(from_Cities);
    }
    
}
