package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.requestcommands.HotFiltersRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommand;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * class for updating elements in elements pull
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ShortUpdatingJob extends QuartzJobBean{

    private static final Logger LOG = Logger.getLogger(ShortUpdatingJob.class.getName());

    private RequestCommand command;
    
    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
//        command = getRequestCommand();
//        if(command!=null){
//            command.execute();
//        }
        LOG.log(Level.INFO, "ShortJob Doing");
        System.out.println("ShortJob Doing");
    }
    
    private RequestCommand getRequestCommand(){
        RequestCommand requestCommand=null;
        ArrayList<RequestCommand> requestsPull = (ArrayList<RequestCommand>) projectConsantsSingletone.getRequestsPull();
                if(requestsPull==null){
            LOG.log(Level.WARNING,"ShortUpdatingJob: requestsList is null");
            return null;
        }
        
        Iterator<RequestCommand> it = requestsPull.iterator();
        while(it.hasNext()){
            
            RequestCommand command = it.next();
            
            if(command instanceof HotFiltersRequestCommand){
                if(!command.getDone()){
                    return command;
                }
            }
            
            if(command instanceof HotSearchRequestCommand){
               if(command.getByHuman()&&!command.getDone()){
                   if(requestCommand==null){
                       requestCommand=command;
                   }else{
                       if(requestCommand.getPriority()<command.getPriority()){
                           requestCommand=command;
                       }
                   }
                   return requestCommand;
               } 
            }
        }
        return requestCommand;
    }
}
