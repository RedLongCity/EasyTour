package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Hotel_Image;
import com.smitsworks.easytour.models.Tour;
import java.util.List;

/**
 *
 * @author redlongcity
 */

public interface Hotel_ImageDao {
    
    List<Hotel_Image> findAll();
    
    Hotel_Image findById(Integer id);
    
    List<Hotel_Image> findByTour(Tour tour);
    
    void save(Hotel_Image hotel_Image);
    
    void mergeHotel_Image(Hotel_Image hotel_Image);
    
    void deleteHotel_Image(Hotel_Image hotel_Image);
}
