package com.smitsworks.easytour.handler.response;

import com.smitsworks.easytour.models.Response;
import com.smitsworks.easytour.command.response.ResponseCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 15/09/2017
 * class for handling response command for It Tours Hot Search
 */

@Service
public class ItToursHotSearchResponseHandler implements ResponseHandler<Response>{

    private static final Logger LOG = Logger.getLogger(ItToursHotSearchResponseHandler.class.getName());

    @Override
    public Response executeResponseCommand(ResponseCommand command) {
        if(command==null){
            LOG.log(Level.WARNING,"ItToursHotSearchResponseHandler: command is null");
            return null;
        }
        return (Response) command.execute();
    }
    
}
