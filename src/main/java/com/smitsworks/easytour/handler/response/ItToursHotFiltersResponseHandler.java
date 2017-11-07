package com.smitsworks.easytour.handler.response;

import com.smitsworks.easytour.models.FiltersResponse;
import com.smitsworks.easytour.command.response.ResponseCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 15/09/2017
 * class for handling response command for It Tours Hot Filters
 */
@Service
public class ItToursHotFiltersResponseHandler implements ResponseHandler<FiltersResponse>{

    private static final Logger LOG = Logger.getLogger(ItToursHotFiltersResponseHandler.class.getName());

    
    @Override
    public FiltersResponse executeResponseCommand(ResponseCommand command) {
        if(command==null){
            LOG.log(Level.WARNING,"ItToursHotFiltersResponseHandler: command is null");
            return null;
        }
        return (FiltersResponse) command.execute();
    }
    
}
