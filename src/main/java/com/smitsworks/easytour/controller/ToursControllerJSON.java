package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.utils.ItToursHotToursFiltersParser;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.JsonView.CountryView;
import com.smitsworks.easytour.JsonView.From_CitiesView;
import com.smitsworks.easytour.JsonView.Hotel_RatingView;
import com.smitsworks.easytour.JsonView.TourView;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.models.Hotel_Rating;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.From_CitiesService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import com.smitsworks.easytour.service.TourService;
import com.smitsworks.easytour.utils.ItToursHotToursSearchParser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
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
    From_CitiesService from_CitiesService;
    
    @Autowired
    Hotel_RatingService hotel_ratingService;
    
    @Autowired
    Meal_TypeService meal_TypeService;
    
    @Autowired
    TourService tourService;
    
    @Autowired
    Scheduler scheduler;
    
    @RequestMapping(value="/stopupdating",method=RequestMethod.GET)
    public void stopScheduling(){
        try {
            scheduler.pauseAll();
        } catch (SchedulerException ex) {
            Logger.getLogger(ToursControllerJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @RequestMapping(value="/startupdating",method=RequestMethod.GET)
    public void startScheduling(){
        try {
            scheduler.resumeAll();
        } catch (SchedulerException ex) {
            Logger.getLogger(ToursControllerJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        searchParser.getTours(1);
        
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
    @JsonView(CountryView.class)
    @RequestMapping(value="/getcountriesforfilter",method=RequestMethod.GET)
    public ResponseEntity<List<Country>> getCountriesForFilter(){
        List<Country> countryList = countryService.findAll();
        if(countryList==null){
            return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Country>>(countryList,HttpStatus.OK);
    }
    @JsonView(From_CitiesView.class)
    @RequestMapping(value="/getcitiesforfilters",method=RequestMethod.GET)
    public ResponseEntity<List<From_Cities>> getFrom_CitiesForFilter(){
        List<From_Cities> from_CitiesList = from_CitiesService.findAll();
        if(from_CitiesList==null){
            return new ResponseEntity<List<From_Cities>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<From_Cities>>(from_CitiesList,HttpStatus.OK);
    } 
    @JsonView(Hotel_RatingView.class)
    @RequestMapping(value="/gethotelratingsforfiters",method=RequestMethod.GET)
    public ResponseEntity<List<Hotel_Rating>> getHotel_RatingsForFilters(){
        List<Hotel_Rating> hotel_RatingList = hotel_ratingService.findAll();
        if(hotel_RatingList==null){
            return new ResponseEntity<List<Hotel_Rating>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Hotel_Rating>>(hotel_RatingList,HttpStatus.OK);
    }
    @JsonView(TourView.class)
    @RequestMapping(value="/getmealtypesforfilter",method=RequestMethod.GET)
    public ResponseEntity<List<Meal_Type>> getMeal_TypesForFilter(){
       List<Meal_Type> meal_TypeList = meal_TypeService.findAll();
       if(meal_TypeList==null){
           return new ResponseEntity<List<Meal_Type>>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<List<Meal_Type>>(meal_TypeList,HttpStatus.OK);
    }
    
}