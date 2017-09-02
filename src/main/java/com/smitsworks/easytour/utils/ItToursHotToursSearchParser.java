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
import com.smitsworks.easytour.service.Hotel_ImageService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import com.smitsworks.easytour.service.PriceService;
import com.smitsworks.easytour.service.TourService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    @Autowired
    Hotel_ImageService hotel_ImageService;
    
    @Autowired
    PriceService priceService;
    
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
        tourService.deleteAllTours();//delete after all
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
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");//Change!
            try {
                Date date = dateFormat.parse((indexNode.path("date_from").asText()));
                tour.setDate_From(date);
            } catch (ParseException ex) {
                Logger.getLogger(ItToursHotToursSearchParser.class.getName()).log(Level.SEVERE, null, ex);
            }
                tour.setDate_From_Unix(indexNode.path("date_from_unix").asInt());
                tour.setCurrency_id(indexNode.path("currency_id").asInt());
                tour.setCurrency_Symbol(indexNode.path("currency_symbol").asText());
                JsonNode pricesNode = indexNode.path("prices");
                    Price price_1 = new Price();
                    Currency currency_1 = currencyService.findById("1");
                    Integer cost_1 = pricesNode.path("1").asInt();
                    price_1.setCurrency(currency_1);
                    price_1.setCost(cost_1);
                    tour.getPrices().add(price_1);
                    Price price_2 = new Price();
                    Currency currency_2 = currencyService.findById("2");
                    Integer cost_2 = pricesNode.path("2").asInt();
                    price_2.setCurrency(currency_2);
                    price_2.setCost(cost_2);
                    tour.getPrices().add(price_2);
                    Price price_10 = new Price();
                    Currency currency_10 = currencyService.findById("10");
                    Integer cost_10 = pricesNode.path("10").asInt();
                    price_10.setCurrency(currency_10);
                    price_10.setCost(cost_10);
                    tour.getPrices().add(price_10);
                tour.setFrom_Cities(from_CitiesService.findById(
                        indexNode.path("from_city_id").asText()));
                tour.setFrom_City_Gen(indexNode.path("from_city_gen").asText());
                tour.setTransport_Type(indexNode.path("transport_type").asText());
                ArrayNode hotel_ImagesNode = (ArrayNode) indexNode.path("hotel_images");
                for(int j=0;j<hotel_ImagesNode.size();j++){
                    Hotel_Image hotel_Image = new Hotel_Image();
                    hotel_Image.setFull(hotel_ImagesNode.get(j).path("full").asText());
                    hotel_Image.setThumb(hotel_ImagesNode.get(j).path("thumb").asText());
                    hotel_Image.setTour(tour);
                    tour.getHotel_ImageSet().add(hotel_Image);
                }
//            for(Price price:tour.getPrices()){
//                priceService.savePrice(price);
//            }
                
            tourService.saveTour(tour);
//            for(Hotel_Image hotel_Image:tour.getHotel_ImageSet()){
//                hotel_ImageService.saveHotel_Image(hotel_Image);
//            }
            
            flag = rootNode.path("has_more_pages").asBoolean();
            if(flag) page++;
    }
        }
        
    }
}
