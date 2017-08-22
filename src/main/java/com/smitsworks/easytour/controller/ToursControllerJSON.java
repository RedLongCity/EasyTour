package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.utils.ItToursParser;
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
    public void findTours(){
        ItToursParser parser = new ItToursParser();
        parser.parseHotTours();
    }
}
