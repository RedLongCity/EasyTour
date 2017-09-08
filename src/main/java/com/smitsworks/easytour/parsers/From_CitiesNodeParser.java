package com.smitsworks.easytour.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.From_CitiesService;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing From_Cities.class from JsonNode
 */
@Service
@Qualifier("from_CitiesNodeParser")
public class From_CitiesNodeParser implements NodeParser{

    private static final Logger LOG = Logger.getLogger(From_CitiesNodeParser.class.getName());

    @Autowired
    From_CitiesService from_CitiesService;
    
    @Autowired
    CountryService countryService;
    
    @Override
    public Boolean parseNode(ArrayNode from_CitiesNode) {
        if(from_CitiesNode.isMissingNode()){
            LOG.log(Level.WARNING,"From_CitiesNode: from_CitiesNode is missing");
            return false;
        }
            for(int i=0;i<from_CitiesNode.size();i++){
            From_Cities from_Cities = new From_Cities();
            JsonNode node;
            
            node=from_CitiesNode.get(i).path("id");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"From_CitiesNode: id node is missing");
                return false; 
            }
            from_Cities.setId(node.asText());
            
            node=from_CitiesNode.get(i).path("name");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"From_CitiesNode: name node is missing");
                return false; 
            }
            from_Cities.setName(node.asText());
            
            from_Cities.setCountrySet(new HashSet<Country>());
            
            node=from_CitiesNode.get(i).path("country_id");
                if(node.isMissingNode()){
                LOG.log(Level.WARNING,"From_CitiesNode: country_id node is missing");
                return false; 
            }
            String[] countriesIdArray = node.asText().split(",",-1);
            for(int j=0;j<countriesIdArray.length;j++){
                String id = countriesIdArray[j];
                Country country = countryService.findById(id);
                if(country!=null){
                    country.getFrom_CitiesSet().add(from_Cities);
                    countryService.updateCountry(country);
                }
            }
        }
        return true; 
    }
    
}
