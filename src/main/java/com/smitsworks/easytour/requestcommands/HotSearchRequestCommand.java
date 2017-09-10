/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smitsworks.easytour.requestcommands;

import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.service.HotSearchRequestHandlerService;
import com.smitsworks.easytour.utils.HttpUtils;
import com.smitsworks.easytour.utils.ItToursHotToursSearchParser;
import com.smitsworks.easytour.utils.ItToursParserConstants;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * class for generalization operations with search information
 */
@Service("requestCommand")
public class HotSearchRequestCommand implements RequestCommand,ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(HotSearchRequestCommand.class.getName());
    
    private JsonNode rootNode;
    private Request request;
    private Integer priority;
    private Boolean done;
    private Boolean byHuman;
    private Boolean areNew;
    private Timestamp requestTime;
    
    @Autowired
    ItToursHotToursSearchParser parser;
    
    @Autowired
    HotSearchRequestHandlerService handlerService;

    public HotSearchRequestCommand(Request request, Integer priority, Boolean done, Boolean byHuman, Boolean areNew) {
        this.request = request;
        this.priority = priority;
        this.done = done;
        this.byHuman = byHuman;
        this.areNew = areNew;
    }



    
    @Override
    public void execute() {
        try {
            rootNode = HttpUtils.getJsonNodeFromUrl(handlerService.
                    getURLByRequest(request));
        } catch (IOException ex) {
            Logger.getLogger(HotSearchRequestCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void parse() {
        parser.extractTours(rootNode);
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    @Override
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public Boolean getDone() {
        return done;
    }

    @Override
    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public Boolean getByHuman() {
        return byHuman;
    }

    @Override
    public void setByHuman(Boolean byHuman) {
        this.byHuman = byHuman;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Boolean getAreNew() {
        return areNew;
    }

    public void setAreNew(Boolean areNew) {
        this.areNew = areNew;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }
    
    
}
