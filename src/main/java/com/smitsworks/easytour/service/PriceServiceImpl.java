package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.PriceDao;
import com.smitsworks.easytour.models.Price;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity
 */

@Service("priceService")
@Transactional
public class PriceServiceImpl implements PriceService{

    @Autowired
    PriceDao priceDao;
    
    @Override
    public Price findById(Integer id) {
        return priceDao.findById(id);
    }

    @Override
    public void savePrice(Price price) {
        priceDao.save(price);
    }

    @Override
    public void updatePrice(Price price) {
        Price entity = priceDao.findById(price.getId());
        entity.setCost(price.getCost());
        entity.setCurrency(price.getCurrency());
        entity.setTour(price.getTour());
        priceDao.mergePrice(entity);
    }

    @Override
    public void deletePrice(Price price) {
        priceDao.deletePrice(price);
    }

    @Override
    public List<Price> findAll() {
        return priceDao.findAll();
    }

    @Override
    public List<Price> findByToursId(Integer id) {
        return priceDao.findByToursId(id);
    }

    @Override
    public void deleteAllPrices() {
        List<Price> priceList = priceDao.findAll();
        if(priceList!=null){
            for(Price price:priceList){
                priceDao.deletePrice(price);
            }
        }
    }
    
}
