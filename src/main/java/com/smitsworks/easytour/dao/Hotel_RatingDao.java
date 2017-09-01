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
    
    void save(Hotel_Rating hotel_Rating);
    
    void mergeHotel_Rating(Hotel_Rating hotel_Rating);
    
    void deleteById(String id);
}
