package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.requestcommands.ItToursSearchBaseRequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 13.09.2017
 * class for handle requests to ItToursSearch
 */
@Service
public class ItToursHotSearchRequestHandler implements RequestHandler{

    @Autowired
    ItToursSearchBaseRequestCommand itToursSearchBaseRequestCommand;
    
    @Autowired
    TimeUtils timeUtils;
    
    @Override
    public void handleRequest(Request request) {
        
    }

    @Override
    public ItToursSearchBaseRequestCommand getBaseRequestCommand() {
        Request request = new Request();
        request.setHotel_Rating("3:78");
        request.setNight_From(2);
        request.setNight_Till(7);
        ItToursSearchBaseRequestCommand command = new ItToursSearchBaseRequestCommand(
        request,1,false,timeUtils.getCurrentTime());
        return command;
    }
    
    
}
