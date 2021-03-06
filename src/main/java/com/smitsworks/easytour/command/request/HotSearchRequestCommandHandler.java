/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smitsworks.easytour.command.request;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.service.RequestService;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * class for handling It Tours Search Requests
 */
@Service
public class HotSearchRequestCommandHandler implements RequestCommandHandler{

    @Autowired
    RequestService service;
    
    private static final Logger LOG = Logger.getLogger(HotSearchRequestCommandHandler.class.getName());

    @Override
    public void removeUnvaluatedTours(RequestCommand requestCommand) {
        Request request = ((HotSearchRequestCommand) requestCommand).getRequest();
                   Set<Tour> tourSet = new HashSet<Tour>();
                   request.setTourSet(tourSet);
                   service.updateRequest(request);
        }
    
}
