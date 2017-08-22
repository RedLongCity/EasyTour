package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Currency;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */

public class CurrencyDaoImpl extends AbstractDao<String,Currency> implements CurrencyDao {

    @Override
    public List<Currency> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<Currency> currencyList = (List<Currency>) crit.list();
        return currencyList;
    }

    @Override
    public Currency findById(String id) {
        Currency currency = getByKey(id);
        return currency;
    }

    @Override
    public void save(Currency currency) {
        persist(currency);
    }

    @Override
    public void deleteById(String id) {
        Currency currency = getByKey(id);
        delete(currency);
    }
    
}
