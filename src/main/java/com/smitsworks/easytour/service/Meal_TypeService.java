package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.Meal_Type;
import java.util.List;
/**
 *
 * @author redlongcity
 */
public interface Meal_TypeService {
    
    Meal_Type findById(String id);
    
    void saveMeal_Type(Meal_Type meal_Type);
    
    void updateMeal_Type(Meal_Type meal_Type);
    
    void deleteMeal_TypeById(String id);
    
    List<Meal_Type> findAll();
    
    void deleteAllMeal_Type();
    
}
