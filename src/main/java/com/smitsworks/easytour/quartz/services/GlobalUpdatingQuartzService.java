/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smitsworks.easytour.quartz.services;

import com.smitsworks.easytour.quartz.jobs.GlobalUpdatingJob;
import com.smitsworks.easytour.quartz.jobs.ShortUpdatingJob;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * class for invoking Global Updating
 */
@Service("quartzService")
public class GlobalUpdatingQuartzService implements QuartzService{

    private static final Logger LOG = Logger.getLogger(GlobalUpdatingQuartzService.class.getName());

    private Scheduler scheduler;
    
//    @Autowired
//    SimpleTriggerFactoryBean simpleTriggerFactoryBean;
//    
//    @Autowired
//    CronTriggerFactoryBean cronTriggerFactoryBean;
//    
//    @Autowired
//    SchedulerFactoryBean schedulerFactoryBean;
//    
    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;
    
    
    
    @Override
    public SimpleTriggerFactoryBean cofigureSimpleTriggerFactory(JobDetail detail) {
        SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
        stFactory.setJobDetail(detail);
        stFactory.setStartDelay(
                projectConsantsSingletone.getShortUpdatingDelay());
        return stFactory;
    }


    @Override
    public CronTriggerFactoryBean configureCronTriggerFactoryBean(JobDetail detail) {
        CronTriggerFactoryBean ctFactory = new CronTriggerFactoryBean();
        ctFactory.setJobDetail(detail);
        ctFactory.setName("globalUpdatingTrigger");
        ctFactory.setGroup("quartzGroup");
        ctFactory.setCronExpression(
                projectConsantsSingletone.getGlobalUpdatingDelay());
        return ctFactory;
    }

    @Override
    public SchedulerFactoryBean configureSchedulerFactoryBean(Trigger...params) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(params);
        return scheduler;
    }

    @Override
    public void updateSimpleTrigger(TriggerKey key,
            Integer RepeatInterval, 
            Integer RepeatCount) {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        Trigger oldTrigger = null;
        try {
            oldTrigger = scheduler.getTrigger(key);
        } catch (SchedulerException ex) {
            Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(oldTrigger==null){
           LOG.log(Level.WARNING,"oldTrigger is null");
           return;
        }
        TriggerBuilder tb = oldTrigger.getTriggerBuilder();
        Trigger newTrigger = tb.withSchedule(SimpleScheduleBuilder.
                simpleSchedule().withIntervalInSeconds(RepeatInterval).
                withRepeatCount(RepeatCount)).build();
        try {
            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateCronTrigger(TriggerKey key, 
            String cronExpressions) {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        Trigger oldTrigger = null;
        try {
            oldTrigger = scheduler.getTrigger(key);
        } catch (SchedulerException ex) {
            Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(oldTrigger==null){
           LOG.log(Level.WARNING,"oldTrigger is null");
           return;
        }
        TriggerBuilder tb = oldTrigger.getTriggerBuilder();
        Trigger newTrigger = tb.withSchedule(CronScheduleBuilder.
                cronSchedule(cronExpressions)).build();
        try {
            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start() {
        JobDetailFactoryBean factoryShort = new JobDetailFactoryBean();
        factoryShort.setJobClass(ShortUpdatingJob.class);
        Map<String,Object> map = new HashMap<String,Object>();
        factoryShort.setJobDataAsMap(map);
        factoryShort.setGroup("group");
        factoryShort.setName("shortUpdatingJob");
        
        JobDetailFactoryBean factoryGlobal = new JobDetailFactoryBean();
        factoryShort.setJobClass(GlobalUpdatingJob.class);
        factoryShort.setJobDataAsMap(map);
        factoryShort.setGroup("group");
        factoryShort.setName("globalUpdatingJob");
        Trigger trigger1=cofigureSimpleTriggerFactory(factoryShort.getObject()).getObject();
        Trigger trigger2=configureCronTriggerFactoryBean(factoryGlobal.getObject()).getObject();
        scheduler=configureSchedulerFactoryBean(cofigureSimpleTriggerFactory
        (factoryShort.getObject()).getObject(),configureCronTriggerFactoryBean
        (factoryGlobal.getObject()).getObject()).getScheduler();
            try {
                scheduler.start();
            } catch (SchedulerException ex) {
                Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void stop() {
        if(scheduler!=null){
            try {
                scheduler.shutdown();
            } catch (SchedulerException ex) {
                Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
    }
    
    
}
