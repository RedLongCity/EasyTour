package com.smitsworks.easytour.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Hotel_Rating;
import com.smitsworks.easytour.service.Hotel_RatingService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing Hotel_ratingNode
 */
@Service
public class Hotel_RatingNodeParser implements NodeParser{

    private static final Logger LOG = Logger.getLogger(Hotel_RatingNodeParser.class.getName());

    @Autowired
    Hotel_RatingService hotel_RatingService;
    
    @Override
    public Boolean parseNode(ArrayNode hotel_RatingNode) {
        if(hotel_RatingNode.isMissingNode()){
            LOG.log(Level.WARNING, "Hotel_Rating: hotel_ratingNode is missing");
            return false;
        }
        for(int i=0;i<hotel_RatingNode.size();i++){
            Hotel_Rating hotel_Rating = new Hotel_Rating();
            JsonNode node;
            
            node=hotel_RatingNode.get(i).path("id");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"Hotel_Rating: id node is missing");
                return false; 
            }
            hotel_Rating.setId(node.asText());
            
            node=hotel_RatingNode.get(i).path("name");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"Hotel_Rating: name node is missing");
                return false; 
            }
            hotel_Rating.setName(node.asText());
            
            hotel_RatingService.saveHotel_Rating(hotel_Rating);
        }
        return true;
    }
    
}
