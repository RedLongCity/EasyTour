package com.smitsworks.easytour.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.models.Hotel_Image;
import com.smitsworks.easytour.models.Price;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.CurrencyService;
import com.smitsworks.easytour.service.From_CitiesService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import com.smitsworks.easytour.service.TourService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author redlongcity
 */

@Service
public class ItToursHotToursSearchParser implements ItToursParserConstants {
    
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
    
    private JsonNode parseHotToursSearch(Integer page){
                JsonNode rootNode = null; 
        try {
            rootNode = HttpUtils.getJsonNodeFromUrl(api_base_url+api_showcases+
                    api_showcases_search+api_showcases_hotel_rating+
                    api_showcases_night_from+api_showcases_night_till+
                    api_showcases_page+page+api_showcases_items_per_page+
                    api_showcases_hotel_image);
        } catch (IOException ex) {
            Logger.getLogger(ItToursHotToursSearchParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rootNode;
    }
    
    public void getTours(){
        int page=1;
        boolean flag=true;
        while(flag){
            JsonNode rootNode = parseHotToursSearch(page);
            ArrayNode offersNode = (ArrayNode)rootNode.path("offers");
            if(offersNode.isMissingNode()){
                return;
            }
            for(int i=0;i<offersNode.size();i++){
                JsonNode indexNode = offersNode.get(i);
                Tour tour = new Tour();
                tour.setKey(indexNode.path("key").asText());
                tour.setType(indexNode.path("type").asInt());
                String country_id = indexNode.path("country_id").asText();
                Country country = countryService.findById(country_id);
                tour.setCountry(country);
                tour.setRegion(indexNode.path("region").asText());
                tour.setHotel_id(indexNode.path("hotel_id").asInt());
                tour.setHotel(indexNode.path("hotel").asText());
                tour.setHotel_Rating(hotel_RatingService.findByName(
                        indexNode.path("hotel_rating").asText()));
                tour.setMeal_Type(meal_TypeService.findByName(
                        indexNode.path("meal_type").asText()));
                tour.setRoom_Type(indexNode.path("room_type").asText());
                tour.setAdult_Amount(indexNode.path("adult_amount").asInt());
                tour.setChild_Amount(indexNode.path("child_amount").asInt());
                tour.setAccomodation(indexNode.path("accomodation").asText());
                tour.setDuration(indexNode.path("duration").asInt());
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd");
            try {
                Date date = dateFormat.parse(indexNode.path("date_from").asText());
                tour.setDate_From(date);
            } catch (ParseException ex) {
                Logger.getLogger(ItToursHotToursSearchParser.class.getName()).log(Level.SEVERE, null, ex);
            }
                tour.setDate_From_Unix(indexNode.path("date_from_unix").asInt());
                tour.setCurrency_id(indexNode.path("currency_id").asInt());
                tour.setCurrency_Symbol(indexNode.path("currency_symbol").asText());
                ArrayNode pricesNode = (ArrayNode)indexNode.path("prices");
                for(int j=0;j<pricesNode.size();j++){
                    Price price = new Price();
                    String[] arrayPrices = pricesNode.get(j).asText().split(": ",-1);
                    Currency currency = currencyService.findById(arrayPrices[0]);
                    Integer cost = Integer.parseInt(arrayPrices[0]);
                    price.setCurrency(currency);
                    price.setCost(cost);
                    tour.getPrices().add(price);
                }
                tour.setFrom_Cities(from_CitiesService.findById(
                        indexNode.path("from_city_id").asText()));
                tour.setFrom_City_Gen(indexNode.path("from_city_gen").asText());
                tour.setTransport_Type(indexNode.path("transport_type").asText());
                ArrayNode hotel_ImagesNode = (ArrayNode) indexNode.path("hotel_images");
                for(int j=0;j<hotel_ImagesNode.size();j++){
                    Hotel_Image hotel_Image = new Hotel_Image();
                    hotel_Image.setFull(hotel_ImagesNode.get(j).path("full").asText());
                    hotel_Image.setThumb(hotel_ImagesNode.get(j).path("thumb").asText());
                    tour.getHotel_ImageSet().add(hotel_Image);
                }
            tourService.saveTour(tour);
            }
            
            flag = rootNode.path("has_more_pages").asBoolean();
            if(flag) page++;
    }
        }
        
    }

