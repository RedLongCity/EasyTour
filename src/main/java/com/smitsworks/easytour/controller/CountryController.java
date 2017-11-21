package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.json.view.CountryView;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.service.CountryService;
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
public class CountryController {

    @Autowired
    private CountryService service;

    @JsonView(CountryView.class)
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countryList = service.findAll();
        if (countryList == null) {
            return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Country>>(countryList, HttpStatus.OK);
    }

    @JsonView(CountryView.class)
    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@PathVariable("id") String id) {
        Country country = service.findById(id);
        if (country == null) {
            return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Country>(country, HttpStatus.OK);
    }

    @RequestMapping(value = "/country", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllCountry() {
        service.deleteAllCountries();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCountryById(
            @PathVariable("id") String id) {
        Country country = service.findById(id);
        if (country == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteCountry(country);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
