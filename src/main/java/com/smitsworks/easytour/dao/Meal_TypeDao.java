package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Meal_Type;
import java.util.List;
/**
 *
 * @author redlongcity
 */

public interface Meal_TypeDao {
    
    List<Meal_Type> findAll();
    
    Meal_Type findById(String id);
    
    Meal_Type findByName(String name);
    
    void save(Meal_Type meal_Type);
    
    void mergeMeal_Type(Meal_Type meal_Type);
    
    void deleteMeal_Type(Meal_Type meal_Type);
    
}
