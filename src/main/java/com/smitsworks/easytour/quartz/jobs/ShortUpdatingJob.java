package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.requestcommands.HotFiltersRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommand;
import com.smitsworks.easytour.requestcommands.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import static org.quartz.JobKey.jobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        //projectConsantsSingletone = ProjectConsantsSingletone.getInstance();
//        command = getRequestCommand();
//        if(command!=null){
//            command.execute();
//            projectConsantsSingletone.setGlobalDelay(true);
//        }
        
        LOG.log(Level.INFO, "ShortJob Doing");
        System.out.println("ShortJob Doing");
        projectConsantsSingletone.setGlobalDelay(false);
        pauseItSelf(jec);
    }
    
    private RequestCommand getRequestCommand(){
        ArrayList<RequestCommand> requestsPull = (ArrayList<RequestCommand>) projectConsantsSingletone.getRequestsPull();
                if(requestsPull==null){
            LOG.log(Level.WARNING,"ShortUpdatingJob: requestsList is null");
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
                   projectConsantsSingletone.setRequestUpdating(
                           hotCommand.getRequest());
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
                   projectConsantsSingletone.setRequestUpdating(
                           hotCommand.getRequest());
                   return zeroingCommand(requestCommand); 
                   }
               } 
            }
        }
        return zeroingCommand(requestCommand);
    }
    
    private void pauseItSelf(JobExecutionContext jec){
        Scheduler scheduler = jec.getScheduler();
        if(scheduler==null){
            LOG.log(Level.WARNING,"ShortJob: scheduler is null");
            return;
        }
        try {
            scheduler.pauseJob(jobKey("shortJob","quartzJobs"));
        } catch (SchedulerException ex) {
            Logger.getLogger(ShortUpdatingJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private RequestCommand zeroingCommand(RequestCommand requestCommand){
        RequestCommand command = requestCommand;
        command.setDone(Boolean.TRUE);
        command.setPriority(1);
        return command;
    }
}
