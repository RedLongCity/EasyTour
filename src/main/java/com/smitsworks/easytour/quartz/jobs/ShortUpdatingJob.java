package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.requestcommands.RequestCommand;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.RequestsPullUtils;
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
    
    @Autowired
    RequestsPullUtils pullUtils;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        LOG.log(Level.INFO, "ShortJob Doing");
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
//        command = pullUtils.getNextCommand();
//        if(command!=null){
//            command.execute();
//            projectConsantsSingletone.setGlobalDelay(true);
//        }else{
//        projectConsantsSingletone.setGlobalDelay(false);
        pauseItSelf(jec);
//    }
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
}
