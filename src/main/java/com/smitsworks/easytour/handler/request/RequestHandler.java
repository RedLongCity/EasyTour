package com.smitsworks.easytour.handler.request;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.command.request.RequestCommand;
import com.smitsworks.easytour.command.response.ResponseCommand;

/**
 *
 * @author redlongcity
 * 13.09.2017
 * interface like fundament for next Request Handlers classes
 */

public interface RequestHandler {
    
    ResponseCommand handleRequest(Request request);
    
    RequestCommand getBaseRequestCommand();
}
