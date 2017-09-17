package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import com.smitsworks.easytour.JsonView.CountryView;
import com.smitsworks.easytour.JsonView.From_CitiesView;
import com.smitsworks.easytour.JsonView.Hotel_RatingView;
import com.smitsworks.easytour.JsonView.TourView;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.FiltersResponse;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.models.Hotel_Rating;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Response;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.requestcommands.HotFiltersRequestCommand;
import com.smitsworks.easytour.requestcommands.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.requesthandlers.ItToursHotFiltersRequestHandler;
import com.smitsworks.easytour.requesthandlers.ItToursHotSearchRequestHandler;
import com.smitsworks.easytour.responsecommands.ResponseCommand;
import com.smitsworks.easytour.responsehandlers.ItToursHotFiltersResponseHandler;
import com.smitsworks.easytour.responsehandlers.ItToursHotSearchResponseHandler;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.From_CitiesService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import com.smitsworks.easytour.service.RequestPullElementService;
import com.smitsworks.easytour.service.RequestService;
import com.smitsworks.easytour.service.TourService;
import com.smitsworks.easytour.service.UpdateSessionService;
import com.smitsworks.easytour.utils.HotSearchRequestConverterUtils;
import com.smitsworks.easytour.utils.ItToursHotToursSearchParser;
import com.smitsworks.easytour.utils.TimeUtils;
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
    QuartzService quartzService;
    
    @Autowired
    ItToursSearchBaseRequestCommand command;
    
    @Autowired
    HotSearchRequestConverterUtils converter;
    
    @Autowired
    ItToursHotToursSearchParser parser;
    
    @Autowired
    RequestService requestService;
    
    @Autowired
    RequestPullElementService elementService;
    
    @Autowired
    UpdateSessionService sessionService;
    
    @Autowired
    TimeUtils timeUtils;
    
    @Autowired
    ItToursHotSearchRequestHandler searchRequestHandler;
    
    @Autowired
    ItToursHotSearchResponseHandler searchResponseHandler;
    
    @Autowired
    ItToursHotFiltersRequestHandler filtersRequestHandler;
    
    @Autowired
    ItToursHotFiltersResponseHandler filtersResponseHandler;
            
    @RequestMapping(value="/do",method=RequestMethod.GET)
    public void doSomething(){
        Request request = new Request();
        Country country = countryService.findById("318");
        request.setCountry(country);
        From_Cities from_Citites = from_CitiesService.findById("2014");
        request.setFrom_Cities(from_Citites);
        request.setNight_From(2);
        request.setNight_Till(4);
        request.setHotel_Rating("3:78");
        searchRequestHandler.handleRequest(request);
    }
    
    @RequestMapping(value="/anotherdo",method=RequestMethod.GET)
    public void doSomethingAnother(){
        Request request = new Request();
        request.setNight_From(2);
        request.setNight_Till(4);
        request.setHotel_Rating("3:78");
        searchRequestHandler.handleRequest(request);
    }
    
    @RequestMapping(value="/shutdown",method=RequestMethod.GET)
    public void stopScheduling(){
        quartzService.shutDown();
    }
    
    @RequestMapping(value="/stop",method=RequestMethod.GET)
    public void pauseScheduling(){
        quartzService.pauseAll();
    }
    
    @RequestMapping(value="/startupdating",method=RequestMethod.GET)
    public void startScheduling(){
    }
    
    @RequestMapping(value="/stopshort",method=RequestMethod.GET)
    public void pauseShort(){
        quartzService.pauseJob("shortJob", "quartzJobs");
    }
    
    @RequestMapping(value="/stopglobal",method=RequestMethod.GET)
    public void pauseGlobal(){
        quartzService.pauseJob("globalJob", "quartzJobs");
    }
    
    @RequestMapping(value="/resumeshort",method=RequestMethod.GET)
    public void resumeShort(){
        quartzService.resumeJob("shortJob", "quartzJobs");
    }
    
    @RequestMapping(value="/resumeglobal",method=RequestMethod.GET)
    public void resumeGlobal(){
        quartzService.resumeJob("globalJob", "quartzJobs");
    }
    
    @RequestMapping(value="/getfilters", method=RequestMethod.GET)
    public ResponseEntity<FiltersResponse> getFilters(){
        ResponseCommand command = filtersRequestHandler.handleRequest(new Request());
        FiltersResponse response = filtersResponseHandler.executeResponseCommand(command);
        if(response==null){
            return new ResponseEntity<FiltersResponse>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<FiltersResponse>(response,HttpStatus.OK);
    }
    
    @RequestMapping(value="/gettours", method=RequestMethod.GET)
    public ResponseEntity<Response> getTours(){
        Request request = new Request();
        Country country = countryService.findById("318");
        request.setCountry(country);
        From_Cities from_Citites = from_CitiesService.findById("2014");
        request.setFrom_Cities(from_Citites);
        request.setNight_From(2);
        request.setNight_Till(4);
        request.setHotel_Rating("3:78");
        ResponseCommand command = searchRequestHandler.handleRequest(request);
        Response response = searchResponseHandler.executeResponseCommand(command);
        if(response==null){
            return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Response>(response,HttpStatus.OK);
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