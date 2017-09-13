package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.RequestPullElement;
import com.smitsworks.easytour.models.UpdateSession;
import com.smitsworks.easytour.requestcommands.HotFiltersRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommand;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import com.smitsworks.easytour.service.RequestPullElementService;
import com.smitsworks.easytour.service.UpdateSessionService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.ItToursHotSearchRequestHandler;
import com.smitsworks.easytour.utils.TimeUtils;
import java.sql.Timestamp;
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
    UpdateSessionService sessionService;
    
    @Autowired
    TimeUtils timeUtils;
    
    @Autowired
    ItToursHotSearchRequestHandler handler;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        //saveAndClearRequests();
        updateTimeConstants();
        LOG.log(Level.INFO, "GlobalJob Doing");
        System.out.println("GlobalJob Doing");
        resumeShortUpdateJob(jec);
    }
    
    private void saveAndClearRequests(){
        ArrayList<RequestCommand> requestsPull = (ArrayList<RequestCommand>) projectConsantsSingletone.getRequestsPull();
        if(requestsPull==null){
            LOG.log(Level.WARNING,"GlobalUpdatingJob: requestsList is null");
            requestsPull = new ArrayList<RequestCommand>();
            requestsPull.add(new HotFiltersRequestCommand(false));
            requestsPull.add(handler.getBaseRequestCommand());
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
            if(command instanceof HotSearchRequestCommand){
                RequestPullElement element = new RequestPullElement();
                Request request = ((HotSearchRequestCommand) command).getRequest();
                element.setRequest(request);
                element.setByHuman(command.getByHuman());
                element.setDone(command.getDone());
                element.setPriority(command.getPriority());
                element.setRequest_pull_DateTime(command.getRequestTime());
                element.setUpdateSession(session);
                session.getRequestPullElementSet().add(element);
                sessionService.updateUpdateSession(session);
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
    
        private void resumeShortUpdateJob(JobExecutionContext jec){
        Scheduler scheduler = jec.getScheduler();
        if(scheduler==null){
            LOG.log(Level.WARNING,"ShortJob: scheduler is null");
            return;
        }
        try {
            scheduler.resumeJob(jobKey("shortJob","quartzJobs"));
        } catch (SchedulerException ex) {
            Logger.getLogger(ShortUpdatingJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        private void updateTimeConstants(){
            projectConsantsSingletone.setTimeOfCurrentSession(
                    timeUtils.getCurrentTime());
            Timestamp timeOfUpdatePreviousSession = projectConsantsSingletone.
                    getTimeOfPreviousSession();
            if(timeOfUpdatePreviousSession==null){
                timeOfUpdatePreviousSession=projectConsantsSingletone.getTimeOfCurrentSession();
            }
        }
        
}
