package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.service.RequestService;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.smitsworks.easytour.utils.RequestConverterUtils;
import java.util.HashSet;
/**
 *
 * @author redlongcity
 */

@Repository("tourDao")
public class TourDaoImpl extends AbstractDao<Integer,Tour> implements TourDao{

    @Autowired
    RequestConverterUtils requestHandlerService;
    
    @Autowired
    RequestService requestService;
    
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
                Hibernate.initialize(tour.getHotel_Rating());
                Hibernate.initialize(tour.getMeal_Type());
                Hibernate.initialize(tour.getRequestSet());
            }
        }
        return tourList;
    }

    @Override
    public List<Tour> getToursBeforeDate(Integer date) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.le("date_From_Unix", date));
        List<Tour> tourList = crit.list();
        if(tourList!=null){
            for(Tour tour:tourList){
                Hibernate.initialize(tour.getCountry());
                Hibernate.initialize(tour.getFrom_Cities());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getHotel_ImageSet());
                Hibernate.initialize(tour.getHotel_Rating());
                Hibernate.initialize(tour.getMeal_Type());
                Hibernate.initialize(tour.getRequestSet()); 
            }
        }
        return tourList;
    }

    @Override
    public List<Tour> getToursBetweenDates(Integer dateBefore, Integer dateTill) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.between("date_From_Unix", dateBefore, dateTill));
        List<Tour> tourList = crit.list();
        if(tourList!=null){
            for(Tour tour:tourList){
                Hibernate.initialize(tour.getCountry());
                Hibernate.initialize(tour.getFrom_Cities());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getHotel_ImageSet());
                Hibernate.initialize(tour.getHotel_Rating());
                Hibernate.initialize(tour.getMeal_Type());
                Hibernate.initialize(tour.getRequestSet()); 
            }
        }
        return tourList;
    }
    
    

    @Override
    public List<Tour> getToursByRequest(Request request) {
        Criteria crit = createCriteria();
        List<Criterion> criterionsList = requestHandlerService.getCriterionsByRequest(request);
        if(criterionsList!=null){
            for(Criterion criterion:criterionsList){
                crit.add(criterion);
            }
        }
        List<Tour> tourList=(List<Tour>) crit.list();
            if(tourList!=null){
        for(Tour tour:tourList){
            Hibernate.initialize(tour.getCountry());
            Hibernate.initialize(tour.getFrom_Cities());
            Hibernate.initialize(tour.getPrices());
            Hibernate.initialize(tour.getHotel_ImageSet());
            Hibernate.initialize(tour.getHotel_Rating());
            Hibernate.initialize(tour.getMeal_Type());
            Hibernate.initialize(tour.getRequestSet());

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
               Hibernate.initialize(tour.getHotel_Rating());
               Hibernate.initialize(tour.getMeal_Type());
               Hibernate.initialize(tour.getRequestSet());

        }
        return tour;
    }

    @Override
    public Tour findByRequest(Request request) {
        Criteria crit = createCriteria();
        List<Criterion> criterionsList = requestHandlerService.getCriterionsByRequest(request);
        if(criterionsList!=null){
            for(Criterion criterion:criterionsList){
                crit.add(criterion);
            }
        }
        Tour tour = (Tour)crit.uniqueResult();
        if(tour!=null){
            Hibernate.initialize(tour.getCountry());
            Hibernate.initialize(tour.getFrom_Cities());
            Hibernate.initialize(tour.getPrices());
            Hibernate.initialize(tour.getHotel_ImageSet());
            Hibernate.initialize(tour.getHotel_Rating());
            Hibernate.initialize(tour.getMeal_Type());
            Hibernate.initialize(tour.getRequestSet());

        }
        return tour;
    }

    @Override
    public Tour findByKey(String key) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("key",key));
        Tour tour = (Tour)crit.uniqueResult();
        if(tour!=null){
            Hibernate.initialize(tour.getCountry());
            Hibernate.initialize(tour.getFrom_Cities());
            Hibernate.initialize(tour.getPrices());
            Hibernate.initialize(tour.getHotel_ImageSet());
            Hibernate.initialize(tour.getHotel_Rating());
            Hibernate.initialize(tour.getMeal_Type());
            Hibernate.initialize(tour.getRequestSet());  
        }
        return tour;
    }

    @Override
    public void save(Tour tour) {
        persist(tour);
    }

    @Override
    public void mergeTour(Tour tour) {
        merge(tour);
    }
    
    @Override
    public void deleteTour(Tour tour) {
        delete(tour);
    }
    
}
