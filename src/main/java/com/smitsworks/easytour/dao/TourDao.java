package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Tour;
import java.sql.Timestamp;
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
    
    List<Tour> getToursBeforeDate(Timestamp date);
    
    List<Tour> getToursBetweenDates(Timestamp dateBefore,
            Timestamp dateTill);
    
    Tour findById(Integer id);
    
    Tour findByRequest(Request request);
    
    Tour findByKey(String key);
    
    void save(Tour tour);
    
    void mergeTour(Tour tour);
    
    void deleteTour(Tour tour);
    
}
