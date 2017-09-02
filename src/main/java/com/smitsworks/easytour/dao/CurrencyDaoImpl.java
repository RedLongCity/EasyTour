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

@Repository("CurrencyDao")
public class CurrencyDaoImpl extends AbstractDao<String,Currency> implements CurrencyDao {

    @Override
    public List<Currency> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<Currency> currencyList = (List<Currency>) crit.list();
        if(currencyList!=null){
            for(Currency currency:currencyList){
                Hibernate.initialize(currency.getPrices());
            }
        }
        return currencyList;
    }

    @Override
    public Currency findById(String id) {
        Currency currency = getByKey(id);
        if(currency!=null){
            Hibernate.initialize(currency.getPrices());
        }
        return currency;
    }

    @Override
    public void save(Currency currency) {
        persist(currency);
    }

    @Override
    public void mergeCurrency(Currency currency) {
        merge(currency);
    }

    @Override
    public void deleteCurrency(Currency currency) {
        delete(currency);
    }
    
}
