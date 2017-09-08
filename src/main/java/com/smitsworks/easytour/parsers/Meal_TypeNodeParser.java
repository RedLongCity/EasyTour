package com.smitsworks.easytour.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.service.Meal_TypeService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing Meal_Type.class from JsonNode
 */

@Service("nodeParser")
public class Meal_TypeNodeParser implements NodeParser {

    private static final Logger LOG = Logger.getLogger(Meal_TypeNodeParser.class.getName());

    @Autowired
    Meal_TypeService meal_TypeService;
    
    @Override
    public Boolean parseNode(ArrayNode meal_TypeNode) {
        if(meal_TypeNode.isMissingNode()){
            LOG.log(Level.WARNING,"Meal_TypeNode: meal_typeNode is misssing");
            return false;
        }
        for(int i=0; i<meal_TypeNode.size();i++){
            Meal_Type meal_Type = new Meal_Type();
            JsonNode node;
            
            node=meal_TypeNode.get(i).path("id");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"Meal_TypeNode: id node is missing");
                return false;
            }
            meal_Type.setId(node.asText());
            
            node=meal_TypeNode.get(i).path("name");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"Meal_TypeNode: name node is missing");
                return false; 
            }
            meal_Type.setName(node.asText());
            
            node=meal_TypeNode.get(i).path("name_full");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"Meal_TypeNode: full_name node is missing");
                return false; 
            }
            meal_Type.setName_full(node.asText());
            
            meal_TypeService.saveMeal_Type(meal_Type);
        }
        return true;
    }
    
}
