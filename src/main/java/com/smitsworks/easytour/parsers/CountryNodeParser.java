package com.smitsworks.easytour.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.service.CountryService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Country;
import java.util.logging.Level;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing Country.class from JsonNode
 */
@Service
public class CountryNodeParser implements NodeParser{

    private static final Logger LOG = Logger.getLogger(CountryNodeParser.class.getName());

    @Autowired
    CountryService countryService;
    
    @Override
    public Boolean parseNode(ArrayNode countriesNode){
        if(countriesNode.isMissingNode()){
            LOG.log(Level.WARNING, "CountryNode: countriesNode is missing");
            return false;
        }
        for(int i=0; i<countriesNode.size();i++){
            Country country = new Country();
            JsonNode node;
            
            node=countriesNode.get(i).path("id");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"CountryNode: id node is missing");
                return false;
            }
            String id = node.asText();
            if(countryService.findById(id)!=null){
                continue;
            }
            country.setId(id);
            node=countriesNode.get(i).path("name");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"CountryNode: name node is missing");
                return false; 
            }
            country.setName(node.asText());
            
            countryService.saveCountry(country);
        }
        return true;
    }
    
}
