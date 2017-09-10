package com.smitsworks.easytour.quartz.services;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * interface like fundament for next Services
 */
public interface QuartzService {
    
    SimpleTriggerFactoryBean cofigureSimpleTriggerFactory(JobDetail detail);
    
    CronTriggerFactoryBean configureCronTriggerFactoryBean(JobDetail detail);
    
    SchedulerFactoryBean configureSchedulerFactoryBean(Trigger...params);

    void updateSimpleTrigger(
            TriggerKey key,
            Integer RepeatInterval,
            Integer RepeatCount);
    
    void updateCronTrigger(
            TriggerKey key,
            String cronExpressions);
    
    void start();
    
    void stop();
}
