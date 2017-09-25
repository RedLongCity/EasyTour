package com.smitsworks.easytour.controller;

import org.springframework.stereotype.Controller;
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
    
    @RequestMapping(value="/admin",method=RequestMethod.GET)
    public String getAdminPage(){
        return "admin";
    }
    
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public String getSearchPage(){
        return "search";
    }
}
