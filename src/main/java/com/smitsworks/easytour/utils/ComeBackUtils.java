package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.command.request.HotFiltersRequestCommand;
import com.smitsworks.easytour.command.request.HotSearchRequestCommand;
import com.smitsworks.easytour.command.request.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.command.request.RequestCommand;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * class for calculating callback delay
 */
@Service
public class ComeBackUtils {

    private static final Logger LOG = Logger.getLogger(ComeBackUtils.class.getName());
    
    @Autowired
    ProjectConsantsSingletone constants;
    
    public Long calculate(RequestCommand requestCommand){
        long delay=0;
        long shortDelay = constants.getShortUpdatingDelay();
        if(requestCommand.getDone()){
            return delay;
        }
        
        Request request = ((HotSearchRequestCommand) requestCommand).
                                   getRequest();
        Request requestUpdating = constants.getRequestUpdating();
        if(requestUpdating!=null){
            if(requestUpdating.equals(request)){
            delay = 1500;
            return delay;
            }
        }
        
        Long rootDelay = request.getRequestDelay();
        if(rootDelay!=null){
            delay+=rootDelay;
        }else{
            delay+=5000;
        }
        
        ArrayList<RequestCommand> commandList = 
                (ArrayList<RequestCommand>) constants.getRequestsPull();
        Iterator<RequestCommand> it = commandList.iterator();
        while(it.hasNext()){
            
        RequestCommand command = it.next();
            
                if(command instanceof HotFiltersRequestCommand){
                if(!command.getDone()){
                    delay+=shortDelay;
                    continue;
                }
                }
                
                if(command instanceof ItToursSearchBaseRequestCommand){
                if(!command.getDone()){
                    delay+=shortDelay;
                    continue;
                }
                }
                
                if(command instanceof HotSearchRequestCommand){
                    if(command.getByHuman()){
                        if(command.getPriority()<requestCommand.getPriority()){
                           Long requestDelay = ((HotSearchRequestCommand) command).
                                   getRequest().getRequestDelay();
                           if(requestDelay!=null){
                               delay+=requestDelay;
                           }
                           delay+=shortDelay;
                           continue; 
                        }
                    }
                }
            
            
        }
        return delay;
    }
    
}
