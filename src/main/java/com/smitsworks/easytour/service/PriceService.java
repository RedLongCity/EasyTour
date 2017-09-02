package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.Price;
import java.util.List;

/**
 *
 * @author redlongcity
 */

public interface PriceService {
    
    Price findById(Integer id);
    
    void savePrice(Price price);
    
    void updatePrice(Price price);
    
    void deletePrice(Price price);
    
    List<Price> findAll();
    
    List<Price> findByToursId(Integer id);
    
    void deleteAllPrices();
    
}
