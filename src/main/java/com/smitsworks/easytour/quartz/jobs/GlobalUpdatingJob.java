package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.RequestsPullUtils;
import com.smitsworks.easytour.utils.TimeUtils;
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
    
    @Autowired
    ProjectConsantsSingletone constants;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        LOG.log(Level.INFO, "GlobalJob Doing");
//        constants.setGlobalRun(true);
//        timeUtils.updateTimeConstants();
//        requestsPullUtils.clearRequestsPull();
//        resumeShortUpdateJob(jec);
        pauseItSelf(jec);
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
            private void pauseItSelf(JobExecutionContext jec){
        constants.setGlobalRun(false);
        Scheduler scheduler = jec.getScheduler();
        if(scheduler==null){
            LOG.log(Level.WARNING,"GlobalUpdatingJob: scheduler is null");
            return;
        }
        try {
            scheduler.pauseJob(jobKey("globalJob","quartzJobs"));
        } catch (SchedulerException ex) {
            Logger.getLogger(ShortUpdatingJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
