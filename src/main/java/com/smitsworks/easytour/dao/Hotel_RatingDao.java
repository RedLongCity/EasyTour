package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Hotel_Rating;
import java.util.List;
/**
 *
 * @author redlongcity
 */

public interface Hotel_RatingDao {
   
    List<Hotel_Rating> findAll();
    
    Hotel_Rating findById(String id);
    
    Hotel_Rating findByName(String name);
    
    void save(Hotel_Rating hotel_Rating);
    
    void mergeHotel_Rating(Hotel_Rating hotel_Rating);
    
    void deleteHotel_Rating(Hotel_Rating hotel_Rating);
}
