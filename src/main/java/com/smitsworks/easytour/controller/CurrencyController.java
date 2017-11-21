package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.json.view.TourView;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.service.CurrencyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity
 * 21/11/2017
 */
@RestController
@RequestMapping("/json")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @JsonView(TourView.class)
    @RequestMapping(value = "/currency", method = RequestMethod.GET)
    public ResponseEntity<List<Currency>> getCurrencies() {
        List<Currency> currencyList = service.findAll();
        if (currencyList == null) {
            return new ResponseEntity<List<Currency>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Currency>>(currencyList, HttpStatus.OK);
    }

    @JsonView(TourView.class)
    @RequestMapping(value = "/currency/{id}", method = RequestMethod.GET)
    public ResponseEntity<Currency> getCurrency(@PathVariable("id") String id) {
        Currency currency = service.findById(id);
        if (currency == null) {
            return new ResponseEntity<Currency>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Currency>(currency, HttpStatus.OK);
    }

    @RequestMapping(value = "/currency", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllCurrencies() {
        service.deleteAllCurrency();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/currency/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCurrencyById(
            @PathVariable("id") String id) {
        Currency currency = service.findById(id);
        if (currency == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteCurrency(currency);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
