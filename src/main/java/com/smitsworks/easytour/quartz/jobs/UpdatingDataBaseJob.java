package com.smitsworks.easytour.quartz.jobs;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author redlongcity
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class UpdatingDataBaseJob extends QuartzJobBean {

    private static final Logger LOG = Logger.getLogger(UpdatingDataBaseJob.class.getName());

    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        LOG.log(Level.INFO, "Job Doing");
        System.out.println("Job Doing");
    }
    
}
