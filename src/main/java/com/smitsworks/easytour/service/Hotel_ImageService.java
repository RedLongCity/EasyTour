package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.Hotel_Image;
import java.util.List;

/**
 *
 * @author redlongcity
 */

public interface Hotel_ImageService {
    
    Hotel_Image findById(Integer id);
    
    void saveHotel_Image(Hotel_Image hotel_Image);
    
    void updateHotel_Image(Hotel_Image hotel_Image);
    
    void deleteHotel_Image(Hotel_Image hotel_Image);
    
    List<Hotel_Image> findAll();
    
    List<Hotel_Image> findByToursId(Integer id);
    
    void deleteAllHotel_Image();
    
}
