/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smitsworks.easytour.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.models.Hotel_Image;
import com.smitsworks.easytour.models.Price;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.CurrencyService;
import com.smitsworks.easytour.service.From_CitiesService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import com.smitsworks.easytour.service.RequestService;
import com.smitsworks.easytour.service.TourService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 09.09.2017
 * Class for getting Tours.class from JsonNode
 */
@Service
public class ToursNodeParser implements NodeParser {

    @Autowired
    TourService tourService;
    
    @Autowired
    CountryService countryService;
    
    @Autowired
    Hotel_RatingService hotel_RatingService;
    
    @Autowired
    Meal_TypeService meal_TypeService;
    
    @Autowired
    CurrencyService currencyService;
    
    @Autowired
    From_CitiesService from_CitiesService;
    
    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;
    
    @Autowired
    RequestService requestService;
    
    private static final Logger LOG = Logger.getLogger(ToursNodeParser.class.getName());
    
    @Override
    public Boolean parseNode(ArrayNode offersNode) {
        if(offersNode.isMissingNode()){
            LOG.log(Level.WARNING,"OffersNode: offersNode is missing");
            return false;
        }
        for(int i=0;i<offersNode.size();i++){
                JsonNode indexNode = offersNode.get(i);
                if(indexNode.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: indexNode is missing");
                return false; 
                }
                Tour tour = new Tour();
                JsonNode node;
                
                node=indexNode.path("key");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: keyNode is missing");
                return false; 
                }
                String key = node.asText();
                Tour entity = tourService.findByKey(key);
                if(entity!=null){
                    Request request = projectConsantsSingletone.getRequestUpdating();
                    if(request==null){
                        LOG.log(Level.WARNING,"OffersNode: request is missing");
                        return false;  
                    }
                    entity.getRequestSet().add(request);
                    tourService.updateTour(entity);
                    continue;
                }
                tour.setKey(key);
                
                node=indexNode.path("type");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: typeNode is missing");
                return false; 
                }
                tour.setType(node.asInt());
                
                node=indexNode.path("country_id");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: country_idNode is missing");
                return false; 
                }
                String country_id = node.asText();
                Country country = countryService.findById(country_id);
                tour.setCountry(country);
                
                node=indexNode.path("region");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: regionNode is missing");
                return false; 
                }
                tour.setRegion(node.asText());
                
                node=indexNode.path("hotel_id");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: hotel_idNode is missing");
                return false; 
                }
                tour.setHotel_id(node.asInt());
                
                node=indexNode.path("hotel");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: hotelNode is missing");
                return false; 
                }
                tour.setHotel(node.asText());
                
                node=indexNode.path("hotel_rating");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: hotel_ratingNode is missing");
                return false; 
                }
                tour.setHotel_Rating(hotel_RatingService.findByName(node.asText()));
                
                node=indexNode.path("meal_type");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: meal_typeNode is missing");
                return false; 
                }
                tour.setMeal_Type(meal_TypeService.findByName(node.asText()));
                
                node=indexNode.path("room_type");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: room_typeNode is missing");
                return false; 
                }
                tour.setRoom_Type(node.asText());
                
                node=indexNode.path("adult_amount");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: adult_amountNode is missing");
                return false; 
                }
                tour.setAdult_Amount(node.asInt());
                
                node=indexNode.path("child_amount");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: child_amountNode is missing");
                return false; 
                }
                tour.setChild_Amount(node.asInt());
                
                node=indexNode.path("accomodation");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: accomodationNode is missing");
                return false; 
                }
                tour.setAccomodation(node.asText());
                
                node=indexNode.path("duration");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: durationNode is missing");
                return false; 
                }
                tour.setDuration(node.asInt());
                
                node=indexNode.path("date_from");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: date_fromNode is missing");
                return false; 
                }
                Date date = Date.valueOf(LocalDate.parse(node.asText()));
                tour.setDate_From(date);
                
                node=indexNode.path("date_from_unix");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: date_from_unixNode is missing");
                return false; 
                }
                tour.setDate_From_Unix(node.asInt());
                
                node=indexNode.path("currency_id");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: currency_idNode is missing");
                return false; 
                }
                tour.setCurrency_id(node.asInt());
                
                node=indexNode.path("currency_symbol");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: currency_symbolNode is missing");
                return false; 
                }
                tour.setCurrency_Symbol(node.asText());
                
                node=indexNode.path("prices");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: pricesNode is missing");
                return false; 
                }
                
                JsonNode pricesNode = node;
                
                Price price_1 = new Price();
                Currency currency_1 = currencyService.findById("1");
                    
                node=pricesNode.path("1");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"OffersNode: path_1_Node is missing");
                return false; 
                }
                    Integer cost_1 = node.asInt();
                    price_1.setTour(tour);
                    price_1.setCurrency(currency_1);
                    price_1.setCost(cost_1);
                    tour.getPrices().add(price_1);
                    
                    Price price_2 = new Price();
                    Currency currency_2 = currencyService.findById("2");
                    
                    node=pricesNode.path("2");
                    if(node.isMissingNode()){
                    LOG.log(Level.WARNING,"OffersNode: path_2_Node is missing");
                    return false; 
                    }
                    Integer cost_2 = node.asInt();
                    price_2.setTour(tour);
                    price_2.setCurrency(currency_2);
                    price_2.setCost(cost_2);
                    tour.getPrices().add(price_2);
                    
                    Price price_10 = new Price();
                    Currency currency_10 = currencyService.findById("10");
                    
                    node=pricesNode.path("10");
                    if(node.isMissingNode()){
                    LOG.log(Level.WARNING,"OffersNode: path_10_Node is missing");
                    return false; 
                    }
                    Integer cost_10 = node.asInt();
                    price_10.setTour(tour);
                    price_10.setCurrency(currency_10);
                    price_10.setCost(cost_10);
                    tour.getPrices().add(price_10);
                    
                node = indexNode.path("price_old");
                if(!node.isMissingNode()){
                    tour.setPrice_Old(node.asInt());
                }
                
                node = indexNode.path("price_change_percent");
                if(!node.isMissingNode()){
                    tour.setPrice_Change_Percent(node.floatValue());
                }
                
                node = indexNode.path("from_city_id");
                if(!node.isMissingNode()){
                tour.setFrom_Cities(from_CitiesService.findById(node.asText()));
                }
                
                node = indexNode.path("from_city_gen");
                if(!node.isMissingNode()){
                tour.setFrom_City_Gen(node.asText());
                }
                
                node = indexNode.path("transport_type");
                if(!node.isMissingNode()){
                   tour.setTransport_Type(node.asText()); 
                }
                
                node = indexNode.path("hotel_images");
                if(!node.isMissingNode()){
                ArrayNode hotel_ImagesNode = (ArrayNode) node;
                for(int j=0;j<hotel_ImagesNode.size();j++){
                    Hotel_Image hotel_Image = new Hotel_Image();
                    hotel_Image.setFull(hotel_ImagesNode.get(j).path("full").asText());
                    hotel_Image.setThumb(hotel_ImagesNode.get(j).path("thumb").asText());
                    hotel_Image.setTour(tour);
                    tour.getHotel_ImageSet().add(hotel_Image);
                }    
                }
            Request request = projectConsantsSingletone.getRequestUpdating();
            tour.getRequestSet().add(request);
            tourService.saveTour(tour);
    } 
        return true;
    }
    
}
