package com.smitsworks.easytour.handler.response;

import com.smitsworks.easytour.models.Response;
import com.smitsworks.easytour.command.response.ResponseCommand;

/**
 *
 * @author redlongcity
 * 15/09/2017
 * fundamentsl interface for Response Handlers
 */

public interface ResponseHandler<T> {

    T executeResponseCommand(ResponseCommand command);
    
}
