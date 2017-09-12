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
    
    void resumeAll();
    
    void pauseAll();
}
