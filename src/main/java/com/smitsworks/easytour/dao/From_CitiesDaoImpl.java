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
        return from_CitiesList;
    }

    @Override
    public From_Cities findById(String id) {
        From_Cities from_Cities = getByKey(id);
        return from_Cities;
    }

    @Override
    public void save(From_Cities from_Cities) {
        persist(from_Cities);
    }

    @Override
    public void deleteById(String id) {
        From_Cities from_Cities = getByKey(id);
        delete(from_Cities);
    }
    
}
