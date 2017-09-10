package com.smitsworks.easytour.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.models.Hotel_Image;
import com.smitsworks.easytour.models.Price;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.parsers.ToursNodeParser;
import com.smitsworks.easytour.service.TourService;
import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author redlongcity
 * 09.09.2017
 * class for fill tours column from ItTours
 */

@Service
public class ItToursHotToursSearchParser implements ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(ItToursHotToursSearchParser.class.getName());
    
    @Autowired
    TourService tourService;
    
    @Autowired
    ToursNodeParser toursNodeParser;
    
//    private JsonNode parseHotToursSearch(Integer page){
//                JsonNode rootNode = null; 
//        try {
//            rootNode = HttpUtils.getJsonNodeFromUrl(api_base_url+api_showcases+
//                    api_showcases_search+api_showcases_hotel_rating+
//                    api_showcases_night_from+api_showcases_night_till+
//                    api_showcases_page+page+api_showcases_items_per_page+
//                    api_showcases_hotel_image);
//        } catch (IOException ex) {
//            Logger.getLogger(ItToursHotToursSearchParser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return rootNode;
//    }
    
    public boolean extractTours(JsonNode rootNode){
            if(rootNode.isMissingNode()){
                LOG.log(Level.WARNING, "rootNode is Missing");
                return false;
            }
            ArrayNode offersNode = (ArrayNode)rootNode.path("offers");
            if(offersNode.isMissingNode()){
                LOG.log(Level.WARNING, "offersNode is Missing");
                return false;
            }
            tourService.deleteAllTours();//Reorginize!
            if(!toursNodeParser.parseNode(offersNode)){
               LOG.log(Level.WARNING,"ToursNodeParser: toursNode Parser returned false");
               return false;
            }
//        JsonNode node;
//        node=rootNode.path("has_more_pages");
//        if(node.isMissingNode()){
//        LOG.log(Level.WARNING,"has_more_pagesNode is missing");
//        return false; 
//        }
//        if(node.asBoolean()){
//            node=rootNode.path("page");
//            if(node.isMissingNode()){
//                LOG.log(Level.WARNING,"pageNode is missing");
//            return false; 
//        }
//            extractTours(node.asInt()+1);
//        }
        LOG.log(Level.INFO, "parsing was finished by success");
        return true;
    }
}
