package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommand;
import com.smitsworks.easytour.requestcommands.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.responsecommands.ItToursHotSearchResponseCommand;
import com.smitsworks.easytour.responsecommands.ResponseCommand;
import com.smitsworks.easytour.service.RequestService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
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
    
    @Autowired
    RequestService requestService;
    
    @Autowired
    ProjectConsantsSingletone constants;
    
    @Autowired
    RequestsPullUtils pullUtils;
    
    @Autowired
    ComeBackUtils backUtils;
    
    @Override
    public ResponseCommand handleRequest(Request request) {
        ResponseCommand responseCommand = null;
        Request entity = requestService.findByFields(request);
        if(entity==null){
            requestService.saveRequest(request);
            HotSearchRequestCommand command = new HotSearchRequestCommand(
            request,1,false,true);
            pullUtils.addRequestCommandToPull(command);
            responseCommand = new ItToursHotSearchResponseCommand(null,
            backUtils.calculate(command));
            return responseCommand;
        }
        if(!pullUtils.isRequestInPull(request)){
            if(pullUtils.isRequestInPreviousPull(request)){
                
            }
        }
        return responseCommand;
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
