package com.smitsworks.easytour.quartz.jobs;

import org.springframework.stereotype.Service;
/**
 *
 * @author redlongcity
 */
@Service("jobone")
public class MyJobOne {
    protected void myTask() {
    	System.out.println("This is my task");
    }
} 
    

