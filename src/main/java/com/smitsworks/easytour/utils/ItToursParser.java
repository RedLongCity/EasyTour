package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.utils.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author redlongcity
 */

public class ItToursParser implements ItToursParserConstants {
    
    public void parseHotTours(){
        JsonNode rootNote = null; 
        try {
            HttpUtils.getJsonNodeFromUrl(api_hot_offers);
        } catch (IOException ex) {
            Logger.getLogger(ItToursParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(rootNote);
    }
    
}
