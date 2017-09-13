package com.smitsworks.easytour.quartz.services;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * interface like fundament for next Services
 */
public interface QuartzService {

    void updateShortTrigger(
            Integer RepeatInterval,
            Integer RepeatCount);
    
    void updateGlobalTrigger(
            String cronExpressions);
    
    void pauseJob(String jobName,String jobGroup);
    
    void resumeJob(String jobName,String jobGroup);
    
    void resumeAll();
    
    void pauseAll();
    
    void shutDown();
}
