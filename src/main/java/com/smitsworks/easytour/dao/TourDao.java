package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Tour;
import java.util.List;
/**
 *
 * @author redlongcity
 * 09.09.2017
 * Interface for dao manipulations
 */

public interface TourDao {
    
        
    List<Tour> findAll();
    
    List<Tour> getToursByRequest(Request request);
    
    Tour findById(Integer id);
    
    Tour findByRequest(Request request);
    
    void save(Tour tour);
    
    void mergeTour(Tour tour);
    
    void deleteTour(Tour tour);
    
}
