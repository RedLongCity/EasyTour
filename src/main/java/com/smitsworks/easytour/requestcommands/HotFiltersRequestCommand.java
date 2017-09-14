package com.smitsworks.easytour.requestcommands;

import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.utils.HttpUtils;
import com.smitsworks.easytour.utils.ItToursHotToursFiltersParser;
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
 * class for generalization operations with filters information
 */
@Service
public class HotFiltersRequestCommand implements RequestCommand,ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(HotFiltersRequestCommand.class.getName());
    
    @Autowired
    ItToursHotToursFiltersParser parser;
    
    private JsonNode rootNode;
    private Boolean done;

    public HotFiltersRequestCommand() {
    }
    
    public HotFiltersRequestCommand(Boolean done) {
        this.done = done;
    }
    
    
    @Override
    public void execute() {
        try {
            rootNode = HttpUtils.getJsonNodeFromUrl(api_base_url+api_showcases+
                    api_showcases_filters);
        } catch (IOException ex) {
            Logger.getLogger(HotFiltersRequestCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        parser.extractHotToursFilters(rootNode);
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
    public void IncreasePriority() {
    }
    

    @Override
    public void setPriority(Integer priority) {
    }

    @Override
    public Integer getPriority() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void setRequestTime(Timestamp time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Timestamp getRequestTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
