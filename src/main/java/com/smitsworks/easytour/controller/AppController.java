package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.RequestPullElementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author redlongcity
 * 17/09/2017
 * controller for configurating and analyse server 
 */
@Controller
@RequestMapping("/")
public class AppController {
    
    @Autowired
    RequestPullElementService elementService;
    
    @Autowired
    CountryService countryService;
    
    @RequestMapping(value="/admin",method=RequestMethod.GET)
    public String listTours(ModelMap model){
    List<Country> countries = countryService.findAll();
    model.addAttribute("countries", countries);
        return "admin";
    }
    
}
