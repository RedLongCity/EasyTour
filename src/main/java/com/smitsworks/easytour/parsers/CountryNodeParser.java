package com.smitsworks.easytour.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.From_Cities;
import java.util.HashSet;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing Country.class from JsonNode
 */
@Service
@Qualifier("countryNodeParser")
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
            country.setId(node.asText());
            
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
