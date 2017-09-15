package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.requestcommands.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.responsecommands.ResponseCommand;

/**
 *
 * @author redlongcity
 * 13.09.2017
 * interface like fundament for next Request Handlers classes
 */

public interface RequestHandler {
    
    ResponseCommand handleSearchRequest(Request request);
    
    ItToursSearchBaseRequestCommand getBaseRequestCommand();
}
