package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.Hotel_ImageDao;
import com.smitsworks.easytour.models.Hotel_Image;
import com.smitsworks.easytour.models.Tour;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("hotel_ImageService")
@Transactional
public class Hotel_ImageServiceImpl implements Hotel_ImageService{

    @Autowired
    Hotel_ImageDao hotel_ImageDao;
    
    @Override
    public Hotel_Image findById(Integer id) {
        return hotel_ImageDao.findById(id);
    }

    @Override
    public void saveHotel_Image(Hotel_Image hotel_Image) {
        hotel_ImageDao.save(hotel_Image);
    }

    @Override
    public void updateHotel_Image(Hotel_Image hotel_Image) {
        Hotel_Image entity = hotel_ImageDao.findById(hotel_Image.getId());
        entity.setFull(hotel_Image.getFull());
        entity.setThumb(hotel_Image.getThumb());
        entity.setTour(hotel_Image.getTour());
        hotel_ImageDao.mergeHotel_Image(entity);
    }

    @Override
    public void deleteHotel_Image(Hotel_Image hotel_Image) {
        hotel_ImageDao.deleteHotel_Image(hotel_Image);
    }

    @Override
    public List<Hotel_Image> findAll() {
        return hotel_ImageDao.findAll();
    }

    @Override
    public List<Hotel_Image> findByTour(Tour tour) {
        return hotel_ImageDao.findByTour(tour);
    }

    @Override
    public void deleteAllHotel_Image() {
        List<Hotel_Image> hotel_ImageList = hotel_ImageDao.findAll();
        if(hotel_ImageList!=null){
            for(Hotel_Image hotel_Image:hotel_ImageList){
                hotel_ImageDao.deleteHotel_Image(hotel_Image);
            }
        }
    }
    
}
