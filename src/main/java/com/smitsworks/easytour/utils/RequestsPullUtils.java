package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.requestcommands.RequestCommand;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * fundamental interface for manipulations with requestsPull
 */
public interface RequestsPullUtils {
    
    RequestCommand getNextCommand();
    
    void addRequestCommandToPull(RequestCommand command);
    
    void clearRequestsPull();
    
    boolean isRequestInPull(Request request);
    
    boolean isRequestInPreviousPull(Request request);
}
