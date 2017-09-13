package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.RequestPullElement;
import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.quartz.services.QuartzServiceImpl;
import com.smitsworks.easytour.requestcommands.HotFiltersRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommand;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import com.smitsworks.easytour.service.RequestPullElementService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import static org.quartz.TriggerKey.triggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * class for construction pull of requests 
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class GlobalUpdatingJob extends QuartzJobBean{

    private static final Logger LOG = Logger.getLogger(GlobalUpdatingJob.class.getName());

    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;
    
    @Autowired
    RequestPullElementService requestPullElementService;
    
    @Autowired
    QuartzService quartzService;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        //saveAndClearRequests();
        LOG.log(Level.INFO, "GlobalJob Doing");
        System.out.println("GlobalJob Doing");
        Scheduler scheduler = jec.getScheduler();
                if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
    }
    
    private void saveAndClearRequests(){
        ArrayList<RequestCommand> requestsPull = (ArrayList<RequestCommand>) projectConsantsSingletone.getRequestsPull();
        if(requestsPull==null){
            LOG.log(Level.WARNING,"GlobalUpdatingJob: requestsList is null");
            return;
        }
        
        Iterator<RequestCommand> it = requestsPull.iterator();
        while(it.hasNext()){
            
            RequestCommand command = it.next();
            
            if(command instanceof HotFiltersRequestCommand){
            command.setDone(Boolean.FALSE);
        }
            if(command instanceof HotSearchRequestCommand){
                RequestPullElement element = new RequestPullElement();
                Request request = ((HotSearchRequestCommand) command).getRequest();
                element.setRequest(request);
                element.setByHuman(command.getByHuman());
                element.setDone(command.getDone());
                element.setPriority(command.getPriority());
                element.setRequest_pull_DateTime(command.getRequestTime());
                requestPullElementService.saveRequestPullElement(element);
                if(command.getPriority()<2&&command.getByHuman()==false){
                    it.remove();
                }else{
                    command.setDone(Boolean.FALSE);
                    command.setByHuman(Boolean.FALSE);
                }
            }
        }
    }
}
