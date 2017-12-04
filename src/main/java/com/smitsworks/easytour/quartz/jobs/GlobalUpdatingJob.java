package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.ConnectionManager;
import com.smitsworks.easytour.utils.RequestsPullUtils;
import com.smitsworks.easytour.utils.TimeUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
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
    
    @Autowired
    QuartzService quartzService;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        ConnectionManager manager = new ConnectionManager();
        LOG.log(Level.INFO, "URL"+manager.getUrl());
        LOG.log(Level.INFO, "Global Job Doing");
        constants.setGlobalSuspended(false);
        timeUtils.updateTimeConstants();
        requestsPullUtils.clearRequestsPull();
        resumeShortUpdateJob(jec);
        //pauseItSelf(jec);
    }
    
        private void resumeShortUpdateJob(JobExecutionContext jec){
        quartzService.resumeShortJob();
    }
            private void pauseItSelf(JobExecutionContext jec){
            constants.setGlobalSuspended(true);
            quartzService.pauseGlobalJob();
    }
}
