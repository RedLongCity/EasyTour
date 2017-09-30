package com.smitsworks.easytour.requestcommands;

import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.service.RequestService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.HotSearchRequestConverterUtils;
import com.smitsworks.easytour.utils.HttpUtils;
import com.smitsworks.easytour.utils.ItToursHotToursSearchParser;
import com.smitsworks.easytour.utils.ItToursParserConstants;
import com.smitsworks.easytour.utils.TimeUtils;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * class for generalization operations with search information
 */
@Service
public class HotSearchRequestCommand implements RequestCommand,ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(HotSearchRequestCommand.class.getName());
    
    private JsonNode rootNode;
    private Request request;
    private Integer priority;
    private Boolean done;
    private Boolean byHuman;
    private Timestamp requestTime;
    
    @Autowired
    ItToursHotToursSearchParser parser;
    
    @Autowired
    HotSearchRequestConverterUtils handlerService;
    
    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;
    
    @Autowired
    TimeUtils timeUtils;
    
    @Autowired
    RequestService service;

    public HotSearchRequestCommand() {
    }

    public HotSearchRequestCommand(Request request, Integer priority, Boolean done, Boolean byHuman) {
        this.request = request;
        this.priority = priority;
        this.done = done;
        this.byHuman = byHuman;
    }
    
    @Override
    public void execute() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        Long delay = timeUtils.getCurrentTime().getTime();
        try {
            rootNode = HttpUtils.getJsonNodeFromUrl(handlerService.
                    getURLByRequest(request));
        } catch (IOException ex) {
            Logger.getLogger(HotSearchRequestCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        projectConsantsSingletone.setRequestUpdating(request);
        parser.extractTours(rootNode);
        delay = timeUtils.getCurrentTime().getTime()-delay;
        request.setRequestDelay(delay);
        service.updateRequest(request);
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
    public void IncreasePriority() {
        this.priority++;
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

    
    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }
    
    
}
