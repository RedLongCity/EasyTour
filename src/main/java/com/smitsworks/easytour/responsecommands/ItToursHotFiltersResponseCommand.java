package com.smitsworks.easytour.responsecommands;

import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.models.FiltersResponse;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.models.Hotel_Rating;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.models.Response;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.CurrencyService;
import com.smitsworks.easytour.service.From_CitiesService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author redlongcity
 * 15/09/2017
 * class for configuration ItTours Filters response command
 */

public class ItToursHotFiltersResponseCommand implements ResponseCommand<FiltersResponse>{

    private static final Logger LOG = Logger.getLogger(ItToursHotFiltersResponseCommand.class.getName());

    @Autowired
    CountryService countryService;
    
    @Autowired
    From_CitiesService from_CititesService;
    
    @Autowired
    Hotel_RatingService hotel_RatingService;
    
    @Autowired
    Meal_TypeService meal_TypeService;
    
    @Autowired
    CurrencyService currencyService;
    
    public ItToursHotFiltersResponseCommand() {
    }

    @Override
    public FiltersResponse execute() {
        FiltersResponse response=null;
        List<Country> countryList = countryService.findAll();
        List<From_Cities> from_CitiesList = from_CititesService.findAll();
        List<Hotel_Rating> hotel_RatingList = hotel_RatingService.findAll();
        List<Meal_Type> meal_TypeList = meal_TypeService.findAll();
        List<Currency> currencyList = currencyService.findAll();
        
        if(countryList!=null){
        response.setCountriesList(countryList);
        }
        if(from_CitiesList!=null){
        response.setFrom_CititesList(from_CitiesList);
        }
        if(hotel_RatingList!=null){
        response.setHotel_RatingList(hotel_RatingList);
        }
        if(meal_TypeList!=null){
        response.setMeal_TypeList(meal_TypeList);
        }
        if(currencyList!=null){
        response.setCurrencyList(currencyList);
        }
        return response;
    }
    
}
