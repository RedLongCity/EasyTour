package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.Hotel_RatingDao;
import com.smitsworks.easytour.models.Hotel_Rating;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("hotel_RatingService")
@Transactional
public class Hotel_RatingServiceImpl implements Hotel_RatingService {

    @Autowired
    Hotel_RatingDao hotel_RatingDao;
   
    @Override
    public Hotel_Rating findById(String id) {
        return hotel_RatingDao.findById(id);
    }

    @Override
    public void saveHotel_Rating(Hotel_Rating hotel_Rating) {
        hotel_RatingDao.save(hotel_Rating);
    }

    @Override
    public void updateHotel_Rating(Hotel_Rating hotel_Rating) {
        Hotel_Rating entity = hotel_RatingDao.findById(hotel_Rating.getId());
        if(entity!=null){
            entity.setName(hotel_Rating.getName());
        }
    }

    @Override
    public void deleteHotel_RatingById(String id) {
        hotel_RatingDao.deleteById(id);
    }

    @Override
    public List<Hotel_Rating> findAll() {
        return hotel_RatingDao.findAll();
    }

    @Override
    public void deleteAllHotel_Rating() {
        List<Hotel_Rating> hotel_RatingList = hotel_RatingDao.findAll();
        if(hotel_RatingList!=null){
            for(Hotel_Rating hotel_Rating:hotel_RatingList){
                hotel_RatingDao.deleteById(hotel_Rating.getId());
        }
    }
}
}
