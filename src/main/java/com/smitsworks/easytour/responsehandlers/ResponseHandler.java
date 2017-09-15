package com.smitsworks.easytour.responsehandlers;

import com.smitsworks.easytour.models.Response;
import com.smitsworks.easytour.responsecommands.ResponseCommand;

/**
 *
 * @author redlongcity
 * 15/09/2017
 * fundamentsl interface for Response Handlers
 */

public interface ResponseHandler<T> {

    T executeResponseCommand(ResponseCommand command);
    
}
