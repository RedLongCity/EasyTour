package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Tour;
import java.util.List;

/**
 *
 * @author redlongcity
 */

public interface TourService {
    
   Tour findById(Integer id);
   
   Tour findByRequest(Request request);
   
   void saveTour(Tour tour);
   
   void updateTour(Tour tour);
   
   void deleteTour(Tour tour);
   
   List<Tour> findAll();
   
   List<Tour> findToursByRequest(Request request);
   
   void deleteAllTours();
    
}
