package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Country;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */

@Repository("countryDao")
public class CountryDaoImpl extends AbstractDao<String,Country> implements CountryDao{

    @Override
    public List<Country> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<Country> countryList = (List<Country>) crit.list();
        return countryList;
    }

    @Override
    public Country findById(String id) {
        Country country = getByKey(id);
        return country;
    }

    @Override
    public void save(Country country) {
        persist(country);
    }

    @Override
    public void deleteById(String id) {
        Country country = getByKey(id);
        delete(country);
    }
    
    
}
