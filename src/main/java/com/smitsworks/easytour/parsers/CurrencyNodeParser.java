package com.smitsworks.easytour.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.service.CurrencyService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 09.09.2017
 * class for parsing Currency.class from JsonNode
 */
@Service("nodeParser")
public class CurrencyNodeParser implements NodeParser{

    private static final Logger LOG = Logger.getLogger(CurrencyNodeParser.class.getName());

    @Autowired
    CurrencyService currencyService;
    
    @Override
    public Boolean parseNode(ArrayNode currencyNode) {
        if(currencyNode.isMissingNode()){
            LOG.log(Level.WARNING,"CurrencyNode: currencyNode is missing");
            return false;
        }
        for(int i=0;i<currencyNode.size();i++){
            Currency currency = new Currency();
            JsonNode node;
            
            node=currencyNode.get(i).path("id");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"CurrencyNode: id node is missing");
                return false;
            }
            currency.setId(node.asText());
            
            node=currencyNode.get(i).path("name");
            if(node.isMissingNode()){
                LOG.log(Level.WARNING,"CurrencyNode: name node is missing");
                return false; 
            }
            currency.setName(node.asText());
            
            currencyService.saveCurrency(currency);
        }
        return true;
    }
    
}
