package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.requesthandlers.ItToursHotSearchRequestHandler;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.RequestPullElement;
import com.smitsworks.easytour.models.UpdateSession;
import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.requestcommands.HotFiltersRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommandHandler;
import com.smitsworks.easytour.requestcommands.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import com.smitsworks.easytour.service.RequestPullElementService;
import com.smitsworks.easytour.service.UpdateSessionService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * class for handling operations with requests pull
 */
@Service("requestsPullUtils")
public class RequestsPullUtilsImpl implements RequestsPullUtils{

    private static final Logger LOG = Logger.getLogger(RequestsPullUtilsImpl.class.getName());

    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;
    
    @Autowired
    ItToursHotSearchRequestHandler handler;
    
    @Autowired
    HotSearchRequestCommandHandler commandHandler;
    
    @Autowired
    UpdateSessionService sessionService;
    
    @Autowired
    RequestPullElementService requestPullElementService;
    
    @Autowired
    TimeUtils timeUtils;
    
    @Autowired
    QuartzService quartzService;
    
    @Override
    public RequestCommand getNextCommand() {
        ArrayList<RequestCommand> requestsPull = (ArrayList<RequestCommand>) projectConsantsSingletone.getRequestsPull();
            if(requestsPull==null){
        LOG.log(Level.WARNING,"getNextCommand: requestsList is null");
        return null;
        }
        RequestCommand requestCommand=null;
        
                
        Iterator<RequestCommand> it = requestsPull.iterator();
        while(it.hasNext()){
            
            RequestCommand command = it.next();
            
            if(command instanceof HotFiltersRequestCommand){
                if(!command.getDone()){
                    return zeroingCommand(command);
                }
            }
            
            if(command instanceof ItToursSearchBaseRequestCommand){
                if(!command.getDone()){
                    return zeroingCommand(command);
                }
            }
            
            if(command instanceof HotSearchRequestCommand){
               if(command.getByHuman()&&!command.getDone()){
                   if(requestCommand==null){
                       requestCommand=command;
                   }else{
                       if(requestCommand.getPriority()>command.getPriority()){
                           requestCommand=command;
                       }
                   }
                   HotSearchRequestCommand hotCommand = (HotSearchRequestCommand)requestCommand;
                   commandHandler.removeUnvaluatedTours(requestCommand);
                   return zeroingCommand(requestCommand);
               }else{
                   if(!command.getDone()){
                       if(requestCommand==null){
                           requestCommand=command;
                       }else{
                           if(requestCommand.getPriority()<command.getPriority()){
                               requestCommand=command;
                           }
                       }
                   HotSearchRequestCommand hotCommand = (HotSearchRequestCommand)requestCommand;
                   commandHandler.removeUnvaluatedTours(requestCommand);
                   return zeroingCommand(requestCommand); 
                   }
               } 
            }
        }if(requestCommand!=null){
        commandHandler.removeUnvaluatedTours(requestCommand);
        return zeroingCommand(requestCommand);
        }
        return requestCommand;
    }

    @Override
    public RequestCommand getCommandByRequest(Request request) {
        ArrayList<RequestCommand> requestsPull = 
                (ArrayList<RequestCommand>) projectConsantsSingletone.getRequestsPull();
        Iterator<RequestCommand> it = requestsPull.iterator();
        while(it.hasNext()){
            RequestCommand command = it.next();
            if(command instanceof HotSearchRequestCommand){
                if(((HotSearchRequestCommand) command).getRequest().equals(request)){
                    return command;
                }
            }
    }
        return null;
    }
    
    

    @Override
    public void addRequestCommandToPull(RequestCommand command) {
        command.setPriority(getNextPriority());
        command.setDone(Boolean.FALSE);
        command.setRequestTime(timeUtils.getCurrentTime());
        projectConsantsSingletone.getRequestsPull().add(command);
        if(!projectConsantsSingletone.isGlobalDelay()){
            quartzService.resumeJob("shortJob","quartzJobs");
        }
    }

    @Override
    public void clearRequestsPull() {
        ArrayList<RequestCommand> requestsPull = (ArrayList<RequestCommand>) projectConsantsSingletone.getRequestsPull();
        if(requestsPull==null||requestsPull.isEmpty()){
            LOG.log(Level.WARNING,"clearRequestsPull: requestsList is null");
            addRequestCommandToPull(new HotFiltersRequestCommand(false));
            addRequestCommandToPull(handler.getBaseRequestCommand());
            return;
        }
        
        UpdateSession session = new UpdateSession();
        session.setSessionTime(projectConsantsSingletone.getTimeOfPreviousSession());
        
        sessionService.saveUpdateSession(session);

        Iterator<RequestCommand> it = requestsPull.iterator();
        while(it.hasNext()){
            
            RequestCommand command = it.next();
            
            if(command instanceof HotFiltersRequestCommand){
            command.setDone(Boolean.FALSE);
        }
            
            if(command instanceof ItToursSearchBaseRequestCommand){
                command.setDone(Boolean.FALSE);
            }
            
            if(command instanceof HotSearchRequestCommand){
                
                if(!command.getDone()){
                    continue;
                }
                
                RequestPullElement element = new RequestPullElement();
                Request request = ((HotSearchRequestCommand) command).getRequest();
                element.setRequest(request);
                element.setByHuman(command.getByHuman());
                element.setDone(command.getDone());
                element.setPriority(command.getPriority());
                element.setRequest_pull_DateTime(command.getRequestTime());
                element.setUpdateSession(session);
                requestPullElementService.saveRequestPullElement(element);
                session.getRequestPullElementSet().add(element);
                sessionService.updateUpdateSession(session);
                if(command.getPriority()<2&&command.getByHuman()==false){
                    commandHandler.removeUnvaluatedTours(command);
                    it.remove();
                }else{
                    command.setDone(Boolean.FALSE);
                    command.setByHuman(Boolean.FALSE);
                }
            }
        }
    }

    @Override
    public boolean isRequestInPull(Request request) {
        ArrayList<RequestCommand> commandList = 
                (ArrayList<RequestCommand>) projectConsantsSingletone.getRequestsPull();
        Iterator it = commandList.iterator();
        while(it.hasNext()){
            RequestCommand command = (RequestCommand) it.next();
            if(command instanceof HotSearchRequestCommand){
                if(((HotSearchRequestCommand) command).getRequest().equals(request)){
                    command.IncreasePriority();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isRequestInPreviousPull(Request request) {
        UpdateSession previousSession = sessionService.getPreviousSession();
        if(previousSession!=null){
        Set<RequestPullElement> elementSet = previousSession.getRequestPullElementSet();
        Iterator<RequestPullElement> it = elementSet.iterator();
        while(it.hasNext()){
            RequestPullElement element = it.next();
            if(element.getRequest().equals(request)){
                return true;
            }
        }
    }
        return false;
    }
    
    
    
    private RequestCommand zeroingCommand(RequestCommand requestCommand){
        RequestCommand command = requestCommand;
        command.setDone(Boolean.TRUE);
        command.setPriority(1);
        return command;
    }
    
    private Integer getNextPriority(){
        Integer priority = 0;
        ArrayList<RequestCommand> commandList = (ArrayList<RequestCommand>) projectConsantsSingletone.
                getRequestsPull();
        Iterator<RequestCommand> it = commandList.iterator();
        while(it.hasNext()){
            RequestCommand command = it.next();
            if(priority==null){
                priority=command.getPriority();
            }else{
                if(priority<command.getPriority()){
                    priority = command.getPriority();
                }
            }
        }
        return ++priority;
    }
}
