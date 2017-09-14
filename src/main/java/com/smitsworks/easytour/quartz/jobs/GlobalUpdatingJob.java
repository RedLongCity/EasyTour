package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.RequestPullElement;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.models.UpdateSession;
import com.smitsworks.easytour.requestcommands.HotFiltersRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommand;
import com.smitsworks.easytour.requestcommands.HotSearchRequestCommandHandler;
import com.smitsworks.easytour.requestcommands.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.requestcommands.RequestCommand;
import com.smitsworks.easytour.service.RequestPullElementService;
import com.smitsworks.easytour.service.TourService;
import com.smitsworks.easytour.service.UpdateSessionService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.ItToursHotSearchRequestHandler;
import com.smitsworks.easytour.utils.RequestsPullUtils;
import com.smitsworks.easytour.utils.TimeUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
    RequestsPullUtils requestsPullUtils;
    
    @Autowired
    TimeUtils timeUtils;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        timeUtils.updateTimeConstants();
        //requestsPullUtils.clearRequestsPull();
        LOG.log(Level.INFO, "GlobalJob Doing");
        resumeShortUpdateJob(jec);
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
}
