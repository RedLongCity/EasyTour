package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Hotel_Rating;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */
@Repository("hotel_RatingDao")
public class Hotel_RatingDaoImpl extends AbstractDao<String,Hotel_Rating> implements Hotel_RatingDao {

    @Override
    public List<Hotel_Rating> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<Hotel_Rating> hotel_RatingList = (List<Hotel_Rating>)crit.list();
        return hotel_RatingList;
    }

    @Override
    public Hotel_Rating findById(String id) {
        Hotel_Rating hotel_Rating = getByKey(id);
        return hotel_Rating;
    }

    @Override
    public void save(Hotel_Rating hotel_Rating) {
        persist(hotel_Rating);
    }

    @Override
    public void deleteById(String id) {
        Hotel_Rating hotel_Rating = getByKey(id);
        delete(hotel_Rating);
    }
    
}
