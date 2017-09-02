package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Tour;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */

@Repository("tourDao")
public class TourDaoImpl extends AbstractDao<Integer,Tour> implements TourDao{

    @Override
    public List<Tour> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("country"));
        List<Tour> tourList = (List<Tour>)crit.list();
        if(tourList!=null){
            for(Tour tour:tourList){
                Hibernate.initialize(tour.getCountry());
                Hibernate.initialize(tour.getFrom_Cities());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getHotel_ImageSet());
            }
        }
        return tourList;
    }

    @Override
    public Tour findById(Integer id) {
        Tour tour = getByKey(id);
        if(tour!=null){
               Hibernate.initialize(tour.getCountry());
               Hibernate.initialize(tour.getFrom_Cities());
               Hibernate.initialize(tour.getPrices());
               Hibernate.initialize(tour.getHotel_ImageSet()); 
        }
        return tour;
    }

    @Override
    public void save(Tour tour) {
        persist(tour);
    }

    @Override
    public void deleteById(Integer id) {
        Tour tour = getByKey(id);
        delete(tour);
    }
    
}
