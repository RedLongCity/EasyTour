package com.smitsworks.easytour.parsers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smitsworks.easytour.models.Request;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * interface for series of parsers node
 */
public interface NodeParser {
    
    Boolean parseNode(ArrayNode arrayNode, Request request);
    
}
