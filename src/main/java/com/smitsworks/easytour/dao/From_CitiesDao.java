package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.From_Cities;
import java.util.List;
/**
 *
 * @author redlongcity
 */

public interface From_CitiesDao {
    
    List<From_Cities> findAll();
    
    From_Cities findById(String id);
    
    void save(From_Cities from_Cities);
    
    void mergeFrom_Cities(From_Cities from_Cities);
    
    void deleteFrom_Cities(From_Cities from_Cities);
    
}
