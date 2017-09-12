/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smitsworks.easytour.quartz.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import static org.quartz.TriggerKey.triggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 12.09.2017
 * class for quartz manipulations
 */
@Service("quartzService")
public class QuartzServiceImpl implements QuartzService {

    private static final Logger LOG = Logger.getLogger(QuartzServiceImpl.class.getName());

    @Autowired
    Scheduler scheduler;
    
    @Override
    public void updateShortTrigger(Integer RepeatInterval, Integer RepeatCount) {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        Trigger oldTrigger = null;
        try {
             oldTrigger = scheduler.getTrigger(triggerKey("shortTrigger","quartzTriggers"));
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        TriggerBuilder tb = oldTrigger.getTriggerBuilder();
        Trigger newTrigger = tb.withSchedule(SimpleScheduleBuilder.
        simpleSchedule().withIntervalInSeconds(RepeatInterval).
        withRepeatCount(RepeatCount)).build();
        try {
            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateGlobalTrigger(String cronExpressions) {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
                Trigger oldTrigger = null;
        try {
            oldTrigger = scheduler.getTrigger(triggerKey("globalTrigger","quartzTriggers"));
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void resumeAll() {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        try {
            scheduler.resumeAll();
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pauseAll() {
        if(scheduler==null){
           LOG.log(Level.WARNING,"Sheduler is null");
           return;
        }
        try {
            scheduler.pauseAll();
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shutDown() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
