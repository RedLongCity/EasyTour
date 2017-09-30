package com.smitsworks.easytour.quartz.services;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * interface like fundament for next Services
 */
public interface QuartzService {

    void updateShortTrigger(
            Long RepeatInterval);
    
    void updateGlobalTrigger(
            String cronExpressions);
    
    void pauseJob(String jobName,String jobGroup);
    
    void pauseGlobalJob();
    
    void pauseShortJob();
    
    void resumeJob(String jobName,String jobGroup);
    
    void resumeGlobalJob();
    
    void resumeShortJob();
    
    void resumeAll();
    
    void pauseAll();
    
    void shutDown();
}
