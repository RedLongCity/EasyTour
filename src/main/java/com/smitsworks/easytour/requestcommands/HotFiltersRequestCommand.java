package com.smitsworks.easytour.requestcommands;

import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.utils.HttpUtils;
import com.smitsworks.easytour.utils.ItToursHotToursFiltersParser;
import com.smitsworks.easytour.utils.ItToursParserConstants;
import java.io.IOException;
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
@Service("requestCommand")
public class HotFiltersRequestCommand implements RequestCommand,ItToursParserConstants {

    private static final Logger LOG = Logger.getLogger(HotFiltersRequestCommand.class.getName());
    
    @Autowired
    ItToursHotToursFiltersParser parser;
    
    private JsonNode rootNode;
    private Integer priority;
    private Boolean done;
    private Boolean byHuman;

    public HotFiltersRequestCommand(Integer priority, Boolean done, Boolean byHuman) {
        this.priority = priority;
        this.done = done;
        this.byHuman = byHuman;
    }
    
    
    @Override
    public void execute() {
        try {
            rootNode = HttpUtils.getJsonNodeFromUrl(api_base_url+api_showcases+
                    api_showcases_filters);
        } catch (IOException ex) {
            Logger.getLogger(HotFiltersRequestCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void parse() {
        parser.extractHotToursFilters(rootNode);
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
    
    
    
}
