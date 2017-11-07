package com.smitsworks.easytour.command.response;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Response;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.service.TourService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * class for configuration ItTours Hot Search response command
 */

public class ItToursHotSearchResponseCommand implements ResponseCommand<Response>{

    private static final Logger LOG = Logger.getLogger(ItToursHotSearchResponseCommand.class.getName());

    private Request request;
    private Long comeBack;
    
    @Autowired
    TourService tourService;

    public ItToursHotSearchResponseCommand(Request request, Long comeBack) {
        this.request = request;
        this.comeBack = comeBack;
    }
    
    @Override
    public Response execute() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        Response response= new Response();
        if(request!=null){
        List<Tour> tourList = tourService.findToursByRequest(request);
        response.setTourList(tourList);
        response.setRequest(request);
        }
        response.setComeBackDelay(comeBack);
        return response;
    }
    
}
