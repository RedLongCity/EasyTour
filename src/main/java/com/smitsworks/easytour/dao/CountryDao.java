package com.smitsworks.easytour.dao;

import java.util.List;
import com.smitsworks.easytour.models.Country;

/**
 *
 * @author redlongcity
 */

public interface CountryDao {
    
    List<Country> findAll();
    
    Country findById(String id);
    
    void save(Country country);
    
    void mergeCountry(Country country);
    
    void deleteById(String id);
}
