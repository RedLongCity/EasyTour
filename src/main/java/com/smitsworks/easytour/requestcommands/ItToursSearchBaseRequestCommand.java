package com.smitsworks.easytour.requestcommands;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.utils.ItToursParserConstants;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 13.09.2017
 * class for wrapping base request
 */
@Service
public class ItToursSearchBaseRequestCommand implements RequestCommand,ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(ItToursSearchBaseRequestCommand.class.getName());

    private Request request;
    private Integer priority;
    private Boolean done;
    private Timestamp requestTime;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    
}
