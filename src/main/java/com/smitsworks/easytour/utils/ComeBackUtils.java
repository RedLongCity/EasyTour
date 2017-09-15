package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.requestcommands.HotFiltersRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommand;
import com.smitsworks.easytour.requestcommands.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.requestcommands.RequestCommand;
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
        if(requestCommand.getDone()){
            return delay;
        }
        ArrayList<RequestCommand> commandList = 
                (ArrayList<RequestCommand>) constants.getRequestsPull();
        Iterator<RequestCommand> it = commandList.iterator();
        while(it.hasNext()){
            
        RequestCommand command = it.next();
            
                if(command instanceof HotFiltersRequestCommand){
                if(!command.getDone()){
                    increaseDelay(delay);
                    continue;
                }
                
                if(command instanceof ItToursSearchBaseRequestCommand){
                if(!command.getDone()){
                    increaseDelay(delay);
                    continue;
                }
                
                if(command instanceof HotSearchRequestCommand){
                    if(command.getByHuman()){
                        if(command.getPriority()<requestCommand.getPriority()){
                           increaseDelay(delay);
                           continue; 
                        }
                    }
                }
            }
            }
        }
        return delay;
    }
    
    private Long increaseDelay(Long delay){
        Long shortUpdatingDelay = constants.getShortUpdatingDelay();
        return delay+shortUpdatingDelay;
    }
    
}
