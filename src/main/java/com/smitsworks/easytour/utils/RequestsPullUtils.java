package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.command.request.RequestCommand;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * fundamental interface for manipulations with requestsPull
 */
public interface RequestsPullUtils {
    
    RequestCommand getNextCommand();
    
    RequestCommand getCommandByRequest(Request request);
    
    void addRequestCommandToPull(RequestCommand command);
    
    void clearRequestsPull();
    
    boolean isRequestInPull(Request request);
    
    boolean isRequestInPreviousPull(Request request);
}
