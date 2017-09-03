package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.utils.ItToursHotToursFiltersParser;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.JsonView.TourView;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.TourService;
import com.smitsworks.easytour.utils.ItToursHotToursSearchParser;
import java.util.List;
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
    ItToursHotToursFiltersParser filterParser;
    
    @Autowired
    ItToursHotToursSearchParser searchParser;
    
    @Autowired
    CountryService countryService;
    
    @Autowired
    TourService tourService;
    
    @RequestMapping(value="/filters", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> getFilters(){
        JsonNode rootNode = filterParser.parseHotToursFilters();
        if(rootNode==null){
            return new ResponseEntity<JsonNode>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<JsonNode>(rootNode, HttpStatus.OK);
    }
    
    @RequestMapping(value="/tours", method=RequestMethod.GET)
    public ResponseEntity<Void> getTours(){
        searchParser.getTours();
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @JsonView(TourView.class)
    @RequestMapping(value="/hottours",method=RequestMethod.GET)
    public ResponseEntity<List<Tour>> getHotTours(){
    List<Tour> tourList = tourService.findAll();
    if(tourList==null){
        return new ResponseEntity<List<Tour>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Tour>>(tourList,HttpStatus.OK);
}
}