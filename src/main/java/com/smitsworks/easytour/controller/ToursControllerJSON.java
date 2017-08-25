package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.utils.ItToursHotToursFiltersParser;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity
 */

@RestController
@RequestMapping("/json")
public class ToursControllerJSON {

    @Autowired
    ItToursHotToursFiltersParser parser;
    
    @Autowired
    CountryService countryService;
    
    @RequestMapping(value="/tours", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> findTours(){
        JsonNode rootNode = parser.parseHotToursFilters();
        if(rootNode==null){
            return new ResponseEntity<JsonNode>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<JsonNode>(rootNode, HttpStatus.OK);
    }
    
    @RequestMapping(value="/somecountry", method=RequestMethod.GET)
    public void saveSomeCountry(){
        Country country = new Country();
        country.setId("1");
        country.setName("Ukraine");
        countryService.saveCountry(country);
        System.out.println("From countryService"+countryService.findAll());
        countryService.deleteAllCountries();
    }
}
