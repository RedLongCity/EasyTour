///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.smitsworks.easytour.quartz.services;
//
//import com.smitsworks.easytour.quartz.jobs.GlobalUpdatingJob;
//import com.smitsworks.easytour.quartz.jobs.ShortUpdatingJob;
//import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SimpleScheduleBuilder;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.TriggerKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author redlongcity
// * 10.09.2017
// * class for invoking Global Updating
// */
//@Service("quartzService")
public class GlobalUpdatingQuartzService {
//
//    private static final Logger LOG = Logger.getLogger(GlobalUpdatingQuartzService.class.getName());
//
//    private Scheduler scheduler;
//    
//    @Autowired
//    ProjectConsantsSingletone projectConsantsSingletone;
//
//    @Override
//    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(
//            String targetBeanName,
//            String targetMethod) {
//        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
//        obj.setTargetBeanName(targetBeanName);
//        obj.setTargetMethod(targetMethod);
//        return obj;
//    }
//
//    @Override
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(
//            JobDetail jobDetail,
//            Integer startDelay, 
//            Integer repeatInterval, 
//            Integer repeatCount) {
//        SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
//        stFactory.setJobDetail(jobDetail);
//        stFactory.setStartDelay(startDelay);
//        stFactory.setRepeatInterval(repeatInterval);
//        stFactory.setRepeatCount(repeatCount);
//        return stFactory;
//    }
//    
//    
//        @Override
//	public JobDetailFactoryBean jobDetailFactoryBean(
//                Class<?> jobClass,
//                Map map,
//                String group,
//                String name){
//		JobDetailFactoryBean factory = new JobDetailFactoryBean();
//		factory.setJobClass(jobClass);
//		factory.setJobDataAsMap(map);
//		factory.setGroup(group);
//		factory.setName(name);
//		return factory;
//	}
//        
//        @Override
//	public CronTriggerFactoryBean cronTriggerFactoryBean(
//                JobDetail jobDetail,
//                Integer startDelay,
//                String name,
//                String group,
//                String cronExpression){
//		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
//		stFactory.setJobDetail(jobDetail);
//		stFactory.setStartDelay(startDelay);
//		stFactory.setName(name);
//		stFactory.setGroup(group);
//		stFactory.setCronExpression(cronExpression);
//		return stFactory;
//	}
//    
//        @Override
//	public SchedulerFactoryBean schedulerFactoryBean(Trigger...triggers) {
//		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
//		scheduler.setTriggers(triggers);
//                return scheduler;
//	}
//
//    @Override
//    public void updateSimpleTrigger(TriggerKey key,
//            Integer RepeatInterval, 
//            Integer RepeatCount) {
//        if(scheduler==null){
//           LOG.log(Level.WARNING,"Sheduler is null");
//           return;
//        }
//        Trigger oldTrigger = null;
//        try {
//            oldTrigger = scheduler.getTrigger(key);
//        } catch (SchedulerException ex) {
//            Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if(oldTrigger==null){
//           LOG.log(Level.WARNING,"oldTrigger is null");
//           return;
//        }
//        TriggerBuilder tb = oldTrigger.getTriggerBuilder();
//        Trigger newTrigger = tb.withSchedule(SimpleScheduleBuilder.
//                simpleSchedule().withIntervalInSeconds(RepeatInterval).
//                withRepeatCount(RepeatCount)).build();
//        try {
//            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
//        } catch (SchedulerException ex) {
//            Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void updateCronTrigger(TriggerKey key, 
//            String cronExpressions) {
//        if(scheduler==null){
//           LOG.log(Level.WARNING,"Sheduler is null");
//           return;
//        }
//        Trigger oldTrigger = null;
//        try {
//            oldTrigger = scheduler.getTrigger(key);
//        } catch (SchedulerException ex) {
//            Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if(oldTrigger==null){
//           LOG.log(Level.WARNING,"oldTrigger is null");
//           return;
//        }
//        TriggerBuilder tb = oldTrigger.getTriggerBuilder();
//        Trigger newTrigger = tb.withSchedule(CronScheduleBuilder.
//                cronSchedule(cronExpressions)).build();
//        try {
//            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
//        } catch (SchedulerException ex) {
//            Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void start() {
//        
//        Trigger shortTrigger;
//        JobDetailFactoryBean shortFactoryBean = jobDetailFactoryBean(
//        ShortUpdatingJob.class,new HashMap<String,Object>(),"group",
//                "shortUpdatingJob");
//        shortTrigger = simpleTriggerFactoryBean(shortFactoryBean.getObject(),
//                0,2,3).getObject();
//        
//        Trigger globalTrigger;
//        JobDetailFactoryBean globalFactoryBean = jobDetailFactoryBean(
//        GlobalUpdatingJob.class,new HashMap<String,Object>(),"group",
//                "globalUpdatingJob");
//        globalTrigger = simpleTriggerFactoryBean(globalFactoryBean.getObject(),
//                2,2,3).getObject();
//        
//        scheduler = schedulerFactoryBean(shortTrigger,globalTrigger).getObject();
//        
//            try {
//                scheduler.start();
//            } catch (SchedulerException ex) {
//                Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
//
//    @Override
//    public void stop() {
//        if(scheduler!=null){
//            try {
//                scheduler.shutdown();
//            } catch (SchedulerException ex) {
//                Logger.getLogger(GlobalUpdatingQuartzService.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }else{
//           LOG.log(Level.WARNING,"Sheduler is null");
//           return;
//        }
//    }
//    
//    
}
