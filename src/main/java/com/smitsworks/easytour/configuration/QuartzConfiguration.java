package com.smitsworks.easytour.configuration;

import com.smitsworks.easytour.quartz.services.*;
import com.smitsworks.easytour.configuration.*;
import com.smitsworks.easytour.quartz.jobs.UpdatingDataBaseJob;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Service;
/**
 *
 * @author redlongcity
 */

//@Configuration
//@ComponentScan("com.smitsworks.easytour.configuration")
//@Service
public class QuartzConfiguration {
//    
//	@Bean
//	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
//		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
////		obj.setTargetBeanName("jobone");
////		obj.setTargetMethod("myTask");
//		return obj;
//	}
//	//Job  is scheduled for 3+1 times with the interval of 30 seconds
//	@Bean
//	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
//		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
////		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
////		stFactory.setStartDelay(3000);
////		stFactory.setRepeatInterval(30000);
////		stFactory.setRepeatCount(3);
//		return stFactory;
//	}
//	@Bean
//	public JobDetailFactoryBean jobDetailFactoryBean(){
//		JobDetailFactoryBean factory = new JobDetailFactoryBean();
////		factory.setJobClass(UpdatingDataBaseJob.class);
////		Map<String,Object> map = new HashMap<String,Object>();
////		factory.setJobDataAsMap(map);
////		factory.setGroup("mygroup");
////		factory.setName("myjob");
//		return factory;
//	}
//	//Job is scheduled after every 1 minute 
//	@Bean
//	public CronTriggerFactoryBean cronTriggerFactoryBean(){
//		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
////		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
////		stFactory.setStartDelay(3000);
////		stFactory.setName("mytrigger");
////		stFactory.setGroup("mygroup");
////		stFactory.setCronExpression("0/10 * * * * ?");
//		return stFactory;
//	}
//	@Bean
//	public SchedulerFactoryBean schedulerFactoryBean() {
//		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
////		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
//                return scheduler;
//	}
}
