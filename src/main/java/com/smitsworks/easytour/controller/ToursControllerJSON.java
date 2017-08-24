package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.utils.ItToursHotToursFiltersParser;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity
 */

@RestController
@RequestMapping("/json")
public class ToursControllerJSON {

    @RequestMapping(value="/tours", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> findTours(){
        ItToursHotToursFiltersParser parser = new ItToursHotToursFiltersParser();
        JsonNode rootNode = parser.parseHotToursFilters();
        if(rootNode==null){
            return new ResponseEntity<JsonNode>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<JsonNode>(rootNode, HttpStatus.OK);
    }
}
