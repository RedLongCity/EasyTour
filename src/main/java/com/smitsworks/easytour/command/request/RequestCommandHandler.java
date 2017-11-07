package com.smitsworks.easytour.command.request;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * interface like fundament for request Handlers
 */

public interface RequestCommandHandler {
  
    void removeUnvaluatedTours(RequestCommand requestCommand);
    
}
