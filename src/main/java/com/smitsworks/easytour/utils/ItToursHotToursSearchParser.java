package com.smitsworks.easytour.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.parsers.ToursNodeParser;
import com.smitsworks.easytour.service.TourService;
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
    public boolean extractTours(JsonNode rootNode, Request request){
            if(rootNode.isMissingNode()){
                LOG.log(Level.WARNING, "rootNode is Missing");
                return false;
            }
            ArrayNode offersNode = (ArrayNode)rootNode.path("offers");
            if(offersNode.isMissingNode()){
                LOG.log(Level.WARNING, "offersNode is Missing");
                return false;
            }
            if(!toursNodeParser.parseNode(offersNode, request)){
               LOG.log(Level.WARNING,"ToursNodeParser: toursNode Parser returned false");
               return false;
            }
        LOG.log(Level.INFO, "parsing was finished by success");
        return true;
    }
}
