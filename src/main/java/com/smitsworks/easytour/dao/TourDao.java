package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Tour;
import java.util.List;
/**
 *
 * @author redlongcity
 */

public interface TourDao {
    
        
    List<Tour> findAll();
    
    Tour findById(Integer id);
    
    void save(Tour tour);
    
    void mergeTour(Tour tour);
    
    void deleteTour(Tour tour);
    
}
