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
import org.springframework.beans.factory.annotation.Qualifier;
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
        if(!parseCountries(countriesNode)){
            LOG.log(Level.WARNING, "Country Parser return false");
            return;
        }
        
        ArrayNode from_CitiesNode = (ArrayNode) rootNode.path("from_cities");
        if(from_CitiesNode.isMissingNode()){
            LOG.log(Level.WARNING,"From_CitiesNode is missing");
            return;
        }
        if(!parseFrom_Cities(from_CitiesNode)){
            LOG.log(Level.WARNING, "From_Cities Parser return false");
            return; 
        }
        ArrayNode hotel_RatingNode = (ArrayNode) rootNode.path("hotel_ratings");
        if(hotel_RatingNode.isMissingNode()){
            LOG.log(Level.WARNING,"Hotel_RatingNode is missing");
            return; 
        }
        if(!parseHotel_rating(hotel_RatingNode)){
            LOG.log(Level.WARNING, "Hotel_RatingNode Parser return false");
            return; 
        }
        ArrayNode meal_TypeNode = (ArrayNode) rootNode.path("meal_types");
        if(meal_TypeNode.isMissingNode()){
            LOG.log(Level.WARNING,"Meal_TypeNode is missing");
            return;
        }
        if(!parseMeal_Type(meal_TypeNode)){
            LOG.log(Level.WARNING, "Meal_TypeNode Parser return false");
            return;
        }
        ArrayNode currencyNode = (ArrayNode) rootNode.path("currencies");
        if(currencyNode.isMissingNode()){
            LOG.log(Level.WARNING,"CurrencyNode is missing");
            return; 
        }
        if(!parseCurrency(currencyNode)){
            LOG.log(Level.WARNING, "CurrencyNode Parser return false");
            return; 
        }
        
         projectConstantsSingletone.setFiltersUpdate(false);//inform application
         //about finish of upadating filters
    }
    
    @Qualifier("countryNodeParser")
    private Boolean parseCountries(ArrayNode countriesNode){
        countryService.deleteAllCountries();
        return nodeParser.parseNode(countriesNode);
    }
    
    @Qualifier("from_CitiesNodeParser")
    private Boolean parseFrom_Cities(ArrayNode from_CitiesNode){
        from_CitiesService.deleteAllFrom_Cities();
        return nodeParser.parseNode(from_CitiesNode);
    }
    
    @Qualifier("hotel_RatingNodeParser")
    private Boolean parseHotel_rating(ArrayNode hotel_RatingNode){
        hotel_RatingService.deleteAllHotel_Rating();
        return nodeParser.parseNode(hotel_RatingNode);
    }
    
    @Qualifier("meal_TypeNodeParser")
    private Boolean parseMeal_Type(ArrayNode meal_TypeNode){
        meal_TypeService.deleteAllMeal_Type();
        return nodeParser.parseNode(meal_TypeNode);
    }
    
    @Qualifier("currencyNodeParser")
    private Boolean parseCurrency(ArrayNode currencyNode){
        currencyService.deleteAllCurrency();
        return nodeParser.parseNode(currencyNode);
    }
}
