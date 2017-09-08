package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Country;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.models.Hotel_Rating;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.parsers.NodeParser;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.CurrencyService;
import com.smitsworks.easytour.service.From_CitiesService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author redlongcity
 */
@Service
public class ItToursHotToursFiltersParser implements ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(ItToursHotToursFiltersParser.class.getName());
    
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
    
    @Autowired
    ProjectConsantsSingletone projectConstantsSingletone;
    
    @Autowired
    NodeParser nodeParser;
    
    public void parseHotToursFilters(){
        JsonNode rootNode = null; 
        try {
            rootNode = HttpUtils.getJsonNodeFromUrl(api_base_url+api_showcases+
                    api_showcases_filters);
        } catch (IOException ex) {
            Logger.getLogger(ItToursHotToursFiltersParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rootNode.isMissingNode()){
            LOG.log(Level.WARNING,"rootNode is Missing");
            return;
        }
        projectConstantsSingletone.setFiltersUpdate(true);//inform application about upadating filters
        
        ArrayNode countriesNode = (ArrayNode) rootNode.path("countries");
        if(countriesNode.isMissingNode()){
            LOG.log(Level.WARNING,"countriesNode is missing");
            return;
        }
        countryService.deleteAllCountries();
        if(!nodeParser.parseNode(countriesNode)){
            LOG.log(Level.WARNING, "Country Parser return false");
            return;
        }
        
        ArrayNode from_CitiesNode = (ArrayNode) rootNode.path("from_cities");
        if(from_CitiesNode.isMissingNode()){
            LOG.log(Level.WARNING,"From_CitiesNode is missing");
            return;
        }
        from_CitiesService.deleteAllFrom_Cities();
        if(!nodeParser.parseNode(from_CitiesNode)){
            LOG.log(Level.WARNING, "From_Cities Parser return false");
            return; 
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
