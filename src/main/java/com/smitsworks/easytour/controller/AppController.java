package com.smitsworks.easytour.controller;

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
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    public String listTours(ModelMap model){
        return "helloworld";
    }
    
}
