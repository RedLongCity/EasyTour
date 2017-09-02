package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.Price;
import java.util.List;
/**
 *
 * @author redlongcity
 */
public interface PriceDao {
    
    List<Price> findAll();
    
    Price findById(Integer id);
    
    List<Price> findByToursId(Integer id);
    
    void save(Price price);
    
    void mergePrice(Price price);
    
    void deletePrice(Price price);
}
