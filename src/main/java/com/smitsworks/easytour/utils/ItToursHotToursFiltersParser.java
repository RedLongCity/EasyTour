package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.utils.HttpUtils;
import com.smitsworks.easytour.models.Country;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.models.Hotel_Rating;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.CurrencyService;
import com.smitsworks.easytour.service.From_CitiesService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service
//@Transactional
public class ItToursHotToursFiltersParser implements ItToursParserConstants {
    
    @Autowired
    CountryService countryService;
    
    @Autowired
    From_CitiesService from_CitiesService;
    
    @Autowired
    Hotel_RatingService hotel_RatingService;
    
    @Autowired
    Meal_TypeService meal_TypeService;
    
    @Autowired
    CurrencyService currencyService;
    
    public JsonNode parseHotToursFilters(){
        JsonNode rootNode = null; 
        try {
            rootNode = HttpUtils.getJsonNodeFromUrl(api_hot_offers);
        } catch (IOException ex) {
            Logger.getLogger(ItToursHotToursFiltersParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        countryService.deleteAllCountries();//Delete after all
        ArrayNode countriesNode = (ArrayNode) rootNode.path("countries");
        for(int i=0; i<countriesNode.size();i++){
            Country country = new Country();
            country.setId(countriesNode.get(i).path("id").asText());
            country.setName(countriesNode.get(i).path("name").asText());
            country.setFrom_CitiesSet(new HashSet<From_Cities>());
            countryService.saveCountry(country);
        }
        from_CitiesService.deleteAllFrom_Cities();//Delete after all
        ArrayNode from_CitiesNode = (ArrayNode) rootNode.path("from_cities");
        for(int i=0;i<from_CitiesNode.size();i++){
            From_Cities from_Cities = new From_Cities();
            from_Cities.setId(from_CitiesNode.get(i).path("id").asText());
            from_Cities.setName(from_CitiesNode.get(i).path("name").asText());
            from_Cities.setCountrySet(new HashSet<Country>());
            //from_CitiesService.saveFrom_Cities(from_Cities);
            String[] countriesIdArray = from_CitiesNode.get(i).path("country_id").
                    asText().split(",",-1);
            for(int j=0;j<countriesIdArray.length;j++){
                String id = countriesIdArray[j];
                Country country = countryService.findById(id);
                if(country!=null){
                    //from_Cities.getCountrySet().add(country);
                    country.getFrom_CitiesSet().add(from_Cities);
                    countryService.updateCountry(country);
                }
            }
        }
        hotel_RatingService.deleteAllHotel_Rating();//delete after all
        ArrayNode hotel_RatingNode = (ArrayNode) rootNode.path("hotel_ratings");
        for(int i=0;i<hotel_RatingNode.size();i++){
            Hotel_Rating hotel_Rating = new Hotel_Rating();
            hotel_Rating.setId(hotel_RatingNode.get(i).path("id").asText());
            hotel_Rating.setName(hotel_RatingNode.get(i).path("name").asText());
            hotel_RatingService.saveHotel_Rating(hotel_Rating);
        }
        meal_TypeService.deleteAllMeal_Type();//delete after all
        ArrayNode meal_TypeNode = (ArrayNode) rootNode.path("meal_types");
        for(int i=0; i<meal_TypeNode.size();i++){
            Meal_Type meal_Type = new Meal_Type();
            meal_Type.setId(meal_TypeNode.get(i).path("id").asText());
            meal_Type.setName(meal_TypeNode.get(i).path("name").asText());
            meal_Type.setName_full(meal_TypeNode.get(i).path("name_full").asText());
            meal_TypeService.saveMeal_Type(meal_Type);
        }
        currencyService.deleteAllCurrency();//delete after all
        ArrayNode currencyNode = (ArrayNode) rootNode.path("currencies");
        for(int i=0;i<currencyNode.size();i++){
            Currency currency = new Currency();
            currency.setId(currencyNode.get(i).path("id").asText());
            currency.setName(currencyNode.get(i).path("name").asText());
            currencyService.saveCurrency(currency);
        }
//        System.out.println("From countryService: "+countryService.findAll());
//        System.out.println("From from_CitiesService: "+from_CitiesService.findAll());
//        System.out.println("From hotel_RatingService: "+hotel_RatingService.findAll());
//        System.out.println("From meal_TypeService: "+meal_TypeService.findAll());
//        System.out.println("From curremcyService: "+currencyService.findAll());
//        System.out.println("RootNode: "+rootNode);
        return rootNode;
    }
    
}
