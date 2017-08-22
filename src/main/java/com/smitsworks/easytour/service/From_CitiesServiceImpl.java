package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.From_CitiesDao;
import com.smitsworks.easytour.models.From_Cities;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("from_citiesService")
@Transactional
public class From_CitiesServiceImpl implements From_CitiesService {

    @Autowired
    From_CitiesDao from_CitiesDao;
    
    @Override
    public From_Cities findById(String id) {
        return from_CitiesDao.findById(id);
    }

    @Override
    public void saveFrom_Cities(From_Cities from_Cities) {
        from_CitiesDao.save(from_Cities);
    }

    @Override
    public void updateFrom_Cities(From_Cities from_Cities) {
        From_Cities entity = from_CitiesDao.findById(from_Cities.getId());
        if(entity!=null){
            entity.setName(from_Cities.getName());
            entity.setCountry_id(from_Cities.getCountry_id());
        }
    }

    @Override
    public void deleteFrom_CitiesById(String id) {
        from_CitiesDao.deleteById(id);
    }

    @Override
    public List<From_Cities> findAll() {
        return from_CitiesDao.findAll();
    }

    @Override
    public void deleteAllFrom_Cities() {
        List<From_Cities> from_CitiesList = from_CitiesDao.findAll();
        if(from_CitiesList!=null){
            for(From_Cities from_Cities:from_CitiesList){
                from_CitiesDao.deleteById(from_Cities.getId());
            }
        }
    }
    
}
