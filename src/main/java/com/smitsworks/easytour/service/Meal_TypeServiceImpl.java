package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.Meal_TypeDao;
import com.smitsworks.easytour.models.Meal_Type;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("meal_TypeService")
@Transactional
public class Meal_TypeServiceImpl implements Meal_TypeService {

    @Autowired
    Meal_TypeDao meal_TypeDao;
    
    @Override
    public Meal_Type findById(String id) {
        return meal_TypeDao.findById(id);
    }

    @Override
    public Meal_Type findByName(String name) {
        return meal_TypeDao.findByName(name);
    }
    
    @Override
    public void saveMeal_Type(Meal_Type meal_Type) {
        meal_TypeDao.save(meal_Type);
    }

    @Override
    public void updateMeal_Type(Meal_Type meal_Type) {
        Meal_Type entity = meal_TypeDao.findById(meal_Type.getId());
        if(entity!=null){
            entity.setName(meal_Type.getName());
            entity.setName_full(meal_Type.getName_full());
            entity.setTours(meal_Type.getTours());
            entity.setRequests(meal_Type.getRequests());
            meal_TypeDao.mergeMeal_Type(entity);
    }
    }

    @Override
    public void deleteMeal_Type(Meal_Type meal_Type) {
        meal_TypeDao.deleteMeal_Type(meal_Type);
    }

    @Override
    public List<Meal_Type> findAll() {
        return meal_TypeDao.findAll();
    }

    @Override
    public void deleteAllMeal_Type() {
        List<Meal_Type> meal_TypeList = meal_TypeDao.findAll();
        if(meal_TypeList!=null){
            for(Meal_Type meal_Type: meal_TypeList){
                meal_TypeDao.deleteMeal_Type(meal_Type);
            }
        }
    }
    
}
