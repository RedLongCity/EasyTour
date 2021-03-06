package com.smitsworks.easytour.handler.request;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.command.request.RequestCommand;
import com.smitsworks.easytour.command.response.ItToursHotFiltersResponseCommand;
import com.smitsworks.easytour.command.response.ResponseCommand;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 17/09/2017
 * class for handle request to ItToursSearch
 */

@Service
public class ItToursHotFiltersRequestHandler implements RequestHandler{

    @Autowired
    ProjectConsantsSingletone constants;

    @Override
    public ResponseCommand handleRequest(Request request) {
        if(constants.isFiltersUpdate()){
            return new ItToursHotFiltersResponseCommand(5000);
        }
            return new ItToursHotFiltersResponseCommand();
    }

    @Override
    public RequestCommand getBaseRequestCommand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
