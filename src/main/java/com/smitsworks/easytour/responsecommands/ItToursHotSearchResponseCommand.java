package com.smitsworks.easytour.responsecommands;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Response;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.service.TourService;
import com.smitsworks.easytour.utils.HotSearchRequestConverterUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * class for configuration response command
 */
public class ItToursHotSearchResponseCommand implements ResponseCommand{

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
        Response response=null;
        if(request!=null){
        List<Tour> tourList = tourService.findToursByRequest(request);
        response.setTourList(tourList);
        }
        response.setComeBackDelay(comeBack);
        return response;
    }
    
}
