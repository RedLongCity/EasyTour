package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.CountryDao;
import com.smitsworks.easytour.dao.From_CitiesDao;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.From_Cities;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService{

    @Autowired
    CountryDao countryDao;
    
    @Autowired
    From_CitiesDao from_CitiesDao;
    
    @Override
    public Country findById(String id) {
        return countryDao.findById(id);
    }

    @Override
    public void saveCountry(Country country) {
        countryDao.save(country);
    }

    @Override
    public void updateCountry(Country country) {
        Country entity = countryDao.findById(country.getId());
        if(entity!=null){
            entity.setName(country.getName());
            entity.setFrom_CitiesSet(country.getFrom_CitiesSet());
            countryDao.mergeCountry(entity);
        }
    }

    @Override
    public void deleteCountryById(String id) {
        countryDao.deleteById(id);
    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public void deleteAllCountries() {
        List<Country> countryList = countryDao.findAll();
        if(countryList!=null){
            for(Country country: countryList){
                countryDao.deleteById(country.getId());
            }
        }
    }
    
}
