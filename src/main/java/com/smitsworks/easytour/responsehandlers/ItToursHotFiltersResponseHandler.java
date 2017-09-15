package com.smitsworks.easytour.responsehandlers;

import com.smitsworks.easytour.models.FiltersResponse;
import com.smitsworks.easytour.responsecommands.ResponseCommand;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author redlongcity
 * 15/09/2017
 * class for handling response command for It Tours Hot Filters
 */
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
