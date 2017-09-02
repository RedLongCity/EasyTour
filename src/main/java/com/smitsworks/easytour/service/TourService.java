package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.Tour;
import java.util.List;

/**
 *
 * @author redlongcity
 */

public interface TourService {
    
   Tour findById(Integer id);
   
   void saveTour(Tour tour);
   
   void updateTour(Tour tour);
   
   void deleteTour(Tour tour);
   
   List<Tour> findAll();
   
   void deleteAllTours();
    
}
