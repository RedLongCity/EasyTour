package com.smitsworks.easytour.configuration;

import com.smitsworks.easytour.quartz.jobs.GlobalUpdatingJob;
import com.smitsworks.easytour.quartz.jobs.ShortUpdatingJob;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
/**
 *
 * @author redlongcity
 */

@Configuration
@ComponentScan("com.smitsworks.easytour")
public class QuartzConfiguration {
    
    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;
    
	@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		stFactory.setJobDetail(jobShortDetailFactoryBean().getObject());
		stFactory.setRepeatInterval(projectConsantsSingletone.
                        getShortUpdatingDelay());
                stFactory.setGroup("quartzTriggers");
                stFactory.setName("shortTrigger");
		return stFactory;
	}
	@Bean
	public JobDetailFactoryBean jobGlobalDetailFactoryBean(){
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(GlobalUpdatingJob.class);
		Map<String,Object> map = new HashMap<String,Object>();
		factory.setJobDataAsMap(map);
		factory.setGroup("quartzJobs");
		factory.setName("globalJob");
		return factory;
	}
        @Bean
	public JobDetailFactoryBean jobShortDetailFactoryBean(){
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(ShortUpdatingJob.class);
		Map<String,Object> map = new HashMap<String,Object>();
		factory.setJobDataAsMap(map);
		factory.setGroup("quartzJobs");
		factory.setName("shortJob");
		return factory;
	}
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobGlobalDetailFactoryBean().getObject());
		stFactory.setName("globalTrigger");
		stFactory.setGroup("quartzTriggers");
//		stFactory.setCronExpression(projectConsantsSingletone.
//                        getGlobalUpdatingDelay());
                stFactory.setCronExpression("0/10 * * * * ?");
		return stFactory;
	}
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(cronTriggerFactoryBean().getObject(),
                        simpleTriggerFactoryBean().getObject());
                return scheduler;
	}
}
