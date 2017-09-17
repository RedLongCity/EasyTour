package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Hotel_Image;
import com.smitsworks.easytour.models.Tour;
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
@Repository("hotel_ImageDao")
public class Hotel_ImageDaoImpl extends AbstractDao<Integer,Hotel_Image> implements Hotel_ImageDao{

    @Override
    public List<Hotel_Image> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("full"));
        List<Hotel_Image> hotel_ImageList = crit.list();
        if(hotel_ImageList!=null){
            for(Hotel_Image hotel_Image:hotel_ImageList){
                Hibernate.initialize(hotel_Image.getTour());
            }
        }
        return hotel_ImageList;
    }

    @Override
    public Hotel_Image findById(Integer id) {
        Hotel_Image hotel_Image = getByKey(id);
        if(hotel_Image!=null){
            Hibernate.initialize(hotel_Image.getTour());
        }
        return hotel_Image;
    }

    @Override
    public List<Hotel_Image> findByTour(Tour tour) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("tour",tour));
        List<Hotel_Image> hotel_ImageList = crit.list();
        if(hotel_ImageList!=null){
            for(Hotel_Image hotel_Image:hotel_ImageList){
                Hibernate.initialize(hotel_Image.getTour());
            }
        }
        return hotel_ImageList;
    }

    @Override
    public void save(Hotel_Image hotel_Image) {
        persist(hotel_Image);
    }

    @Override
    public void mergeHotel_Image(Hotel_Image hotel_Image) {
        merge(hotel_Image);
    }

    @Override
    public void deleteHotel_Image(Hotel_Image hotel_Image) {
        delete(hotel_Image);
    }
    
}
