package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.CurrencyDao;
import com.smitsworks.easytour.models.Currency;
import java.util.List;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity
 */

@Service("currencyService")
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyDao currencyDao;
    
    @Override
    public Currency findById(String id) {
        return currencyDao.findById(id);
    }

    @Override
    public void saveCurrency(Currency currency) {
        currencyDao.save(currency);
    }

    @Override
    public void updateCurrency(Currency currency) {
        Currency entity = currencyDao.findById(currency.getId());
        if(entity!=null){
            entity.setName(currency.getName());
    }
    }

    @Override
    public void deleteCurrencyById(String id) {
        currencyDao.deleteById(id);
    }

    @Override
    public List<Currency> findAll() {
        return currencyDao.findAll();
    }

    @Override
    public void deleteAllCurrency() {
        List<Currency> currencyList = currencyDao.findAll();
        if(currencyList!=null){
            for(Currency currency: currencyList){
                currencyDao.deleteById(currency.getId());
            }
        }
    }
    
}
