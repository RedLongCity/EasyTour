package com.smitsworks.easytour.quartz.jobs;

import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.command.request.RequestCommand;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.RequestsPullUtils;
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
 * class for updating elements in elements pull
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ShortUpdatingJob extends QuartzJobBean{

    private static final Logger LOG = Logger.getLogger(ShortUpdatingJob.class.getName());

    private RequestCommand command;
    
    @Autowired
    ProjectConsantsSingletone constants;
    
    @Autowired
    RequestsPullUtils pullUtils;
    
    @Autowired
    QuartzService quartzService;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 
        LOG.log(Level.INFO, "ShortJob Doing");
        constants.setShortSuspended(false);
        constants.setShortRun(true);
        command = pullUtils.getNextCommand();
        if(command!=null){
            command.execute();
            command.setDone(Boolean.TRUE);
            constants.setGlobalDelay(true);
            constants.setRequestUpdating(null);
        }else{
        constants.setGlobalDelay(false);
        pauseItSelf(jec);
    }
    }
    
    
    private void pauseItSelf(JobExecutionContext jec){
        constants.setShortSuspended(true);
        quartzService.pauseShortJob();
    }
}
