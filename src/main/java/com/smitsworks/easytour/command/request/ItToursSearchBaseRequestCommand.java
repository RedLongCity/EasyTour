package com.smitsworks.easytour.command.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.HotSearchRequestConverterUtils;
import com.smitsworks.easytour.utils.HttpUtils;
import com.smitsworks.easytour.utils.ItToursHotToursSearchParser;
import com.smitsworks.easytour.utils.ItToursParserConstants;
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
 * 13.09.2017
 * class for wrapping base request
 */
@Service
public class ItToursSearchBaseRequestCommand implements RequestCommand,ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(ItToursSearchBaseRequestCommand.class.getName());

    private JsonNode rootNode;
    private Request request;
    private Integer priority;
    private Boolean done;
    private boolean processed;
    private Timestamp requestTime;
    
    @Autowired
    HotSearchRequestConverterUtils converter;
    
    @Autowired
    ItToursHotToursSearchParser parser;
    
    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;

    public ItToursSearchBaseRequestCommand() {
    }

    public ItToursSearchBaseRequestCommand(Request request, Integer priority, Boolean done, Timestamp requestTime) {
        this.request = request;
        this.priority = priority;
        this.done = done;
        this.requestTime = requestTime;
    }
    
    
    @Override
    public void execute() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
                try {
            rootNode = HttpUtils.getJsonNodeFromUrl(converter.
                    getURLByRequest(request));
        } catch (IOException ex) {
            Logger.getLogger(HotSearchRequestCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        projectConsantsSingletone.setRequestUpdating(request);
        parser.extractTours(rootNode);
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public void IncreasePriority() {
        this.priority++;
    }
    
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public void setByHuman(Boolean byHuman) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getByHuman() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isProcessed() {
        return processed;
    }

    @Override
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
    
    
}
