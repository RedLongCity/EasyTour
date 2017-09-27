package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author redlongcity
 * 17/09/2017
 * controller for configurating and analyse server 
 */

@Controller
@RequestMapping("/")
public class AppController {
    
    @Autowired
    QuartzService quartzService;

    @Autowired
    ProjectConsantsSingletone constatns;
    
    @RequestMapping(value="/admin",method=RequestMethod.GET)
    public String getAdminPage(){
        return "admin";
    }
    
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public String getSearchPage(){
        return "search";
    }
    
    @RequestMapping(value="/statistics",method=RequestMethod.GET)
    public String getStatisticsPage(){
        return "statistics";
    }
    
    @RequestMapping(value="/settings",method=RequestMethod.GET)
    public String getSettingsPage(){
        return "settings";
    }
    
    @RequestMapping(value="/stopshort",method=RequestMethod.GET)
    public void stopShort(){
        quartzService.pauseJob("shortJob", "quartzJobs");
        constatns.setShorRun(false);
    }
    
    @RequestMapping(value="/resumeshort",method=RequestMethod.GET)
    public void resumeShort(){
        quartzService.resumeJob("shortJob", "quartzJobs");
    }
    
    @RequestMapping(value="/setshortdelay",method=RequestMethod.GET)
    public void setShortDelay(@RequestParam("delay") Integer delay){
        quartzService.updateShortTrigger(delay, 0);
    }
    
    @RequestMapping(value="/stopglobal",method=RequestMethod.GET)
    public void stopGlobal(){
        quartzService.pauseJob("globalJob", "quartzJobs");
        constatns.setGlobalRun(false);
    }
    
    @RequestMapping(value="/resumeglobal",method=RequestMethod.GET)
    public void resumeGlobal(){
        quartzService.resumeJob("globalJob", "quartzJobs");
    }
    
    @RequestMapping(value="/setglobaldelay",method=RequestMethod.GET)
    public void setShortDelay(@RequestParam("delay") String delay){
        quartzService.updateGlobalTrigger(delay);
    }
}
