package com.smitsworks.easytour.utils;

/**
 *
 * @author redlongcity
 */

public interface ItToursParserConstants {
    
                    //Base 
    
    String api_base_url = "http://api.ittour.com.ua/";
    String authorization = "Authorization";
    String authorization_token = "4a24d8ad6425bdc3c7fcfe1f1d9f63b9";
    String acceptLanguage = "Accept-Language";
    String response_language   = "ru";
    
                    //Showcase
    
    String api_showcases = "showcase/hot-offers";
    String api_showcases_filters = "/filters";
    String api_showcases_search = "/search?";
    String api_showcases_hotel_rating = "hotel_rating=3:78&";
    String api_showcases_night_from = "night_from=1&";
    String api_showcases_night_till = "night_till=10&";
    String api_showcases_page = "&page=";
    String api_showcases_items_per_page = "&items_per_page=100&";
    String api_showcases_hotel_image = "hotel_image=1";
    
                    //Others
    
    String api_params_url = "module/params";
    String api_search_url = "module/search";
    String api_search_by_keys = "module/search-list";
    String api_tour_info_url = "tour/info/";
    String api_tour_validate_url = "tour/validate/";
    String api_tour_flights_urs = "tour/flights/";
    

    
}
