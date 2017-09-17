package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Price;
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
@Repository("priceDao")
public class PriceDaoImpl extends AbstractDao<Integer,Price> implements PriceDao {

    @Override
    public List<Price> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("cost"));
        List<Price> priceList = crit.list();
        if(priceList!=null){
            for(Price price:priceList){
                Hibernate.initialize(price.getTour());
                Hibernate.initialize(price.getCurrency());
            }
        }
        return priceList;
    }

    @Override
    public Price findById(Integer id) {
        Price price = getByKey(id);
        if(price!=null){
                Hibernate.initialize(price.getTour());
                Hibernate.initialize(price.getCurrency());
        }
        return price;
    }

    @Override
    public List<Price> findByToursId(Integer id) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("tour", id));
        List<Price> priceList = crit.list();
        if(priceList!=null){
            for(Price price:priceList){
                Hibernate.initialize(price.getTour());
                Hibernate.initialize(price.getCurrency());
            }
        }
        return priceList;
    }

    @Override
    public void save(Price price) {
        persist(price);
    }

    @Override
    public void mergePrice(Price price) {
        merge(price);
    }

    @Override
    public void deletePrice(Price price) {
        delete(price);
    }
    
}
