package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.From_Cities;
import java.util.List;
/**
 *
 * @author redlongcity
 */

public interface From_CitiesService {
    
    From_Cities findById(String id);
    
    void saveFrom_Cities(From_Cities from_Cities);
    
    void updateFrom_Cities(From_Cities from_Cities);
    
    void deleteFrom_CitiesById(String id);
    
    List<From_Cities> findAll();
    
    void deleteAllFrom_Cities();
    
}
