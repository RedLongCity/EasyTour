package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.models.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 09.09.2017
 * class for handling operations with ItTours Hot Search Request
 */
@Service
public class HotSearchRequestConverterUtils implements RequestConverterUtils,ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(HotSearchRequestConverterUtils.class.getName());

    public HotSearchRequestConverterUtils() {
    }

    
    @Override
    public List<Criterion> getCriterionsByRequest(Request request) {
        List<Criterion> criterionsList = new ArrayList<Criterion>();
        
        Country country = request.getCountry();
        if(country!=null){
            String country_Id = country.getId();
            criterionsList.add(Restrictions.eq("country_id", country_Id));
        }
        
        From_Cities from_Cities = request.getFrom_Cities();
        if(from_Cities!=null){
            String from_Cities_Id = from_Cities.getId();
            criterionsList.add(Restrictions.eq("from_city_id", from_Cities_Id));
        }
        
        String hotel_Rating = request.getHotel_Rating();
        if(hotel_Rating==null){
            LOG.log(Level.WARNING,"RequestHandlerService: Hotel rating is null");
            return criterionsList;
        }
        String[] hotel_RatingsArray = hotel_Rating.split(":",-1);
        if(hotel_RatingsArray.length<2){
            LOG.log(Level.WARNING,"RequestHandlerService: hotel_RatingsArray too short");
            return criterionsList; 
        }
        criterionsList.add(Restrictions.between("hotel_rating_id", 
                hotel_RatingsArray[0], hotel_RatingsArray[1]));
        
        Integer night_From = request.getNight_From();
        if(night_From==null){
            LOG.log(Level.WARNING,"RequestHandlerService: night_From is null");
            return criterionsList; 
        }
        
        Integer night_Till = request.getNight_Till();
        if(night_Till==null){
            LOG.log(Level.WARNING,"RequestHandlerService: night_Till is null");
            return criterionsList; 
        }
        
        criterionsList.add(Restrictions.between("duration", night_From, night_Till));
        
        Meal_Type meal_Type = request.getMeal_Type();
        if(meal_Type!=null){
            String meal_Type_Id = meal_Type.getId();
            criterionsList.add(Restrictions.eq("meal_type_id", meal_Type_Id));
        }
        
        return criterionsList;
    }

    @Override
    public String getURLByRequest(Request request) {
       String URL =  api_base_url//http://api.ittour.com.ua/
               +api_showcases//showcase/hot-offers
               +api_showcases_search;// /search?
       
       Country country = request.getCountry();
        if(country!=null){
            String country_Id = country.getId();
            URL = URL.concat("country="+country_Id+"&");
        }
        
        From_Cities from_Cities = request.getFrom_Cities();
        if(from_Cities!=null){
            String from_Cities_Id = from_Cities.getId();
            URL = URL.concat("from_city="+from_Cities_Id+"&");
        }
        
        String hotel_Rating = request.getHotel_Rating();
        if(hotel_Rating==null){
            LOG.log(Level.WARNING,"RequestHandlerService_URL: Hotel rating is null");
            return URL;
        }
        URL = URL.concat("hotel_rating="+hotel_Rating+"&");
        
        Integer night_From = request.getNight_From();
        if(night_From==null){
            LOG.log(Level.WARNING,"RequestHandlerService: night_From is null");
            return URL; 
        }
        URL=URL.concat("night_from="+night_From+"&");
        
        Integer night_Till = request.getNight_Till();
        if(night_Till==null){
            LOG.log(Level.WARNING,"RequestHandlerService: night_Till is null");
            return URL; 
        }
        URL=URL.concat("night_till="+night_Till+"&");
        
        Meal_Type meal_Type = request.getMeal_Type();
        if(meal_Type!=null){
            String meal_Type_Id = meal_Type.getId();
            URL=URL.concat("meal_type="+meal_Type_Id+"&");
        }
        URL=URL.concat("items_per_page=100&hotel_image=1");
        return URL;
    }
    
}
