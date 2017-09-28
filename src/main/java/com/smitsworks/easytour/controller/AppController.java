package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.GlobalUpdateDelayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @Autowired
    GlobalUpdateDelayUtils delayUtils;
    
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
    public @ResponseBody void stopShort(){
        quartzService.pauseJob("shortJob", "quartzJobs");
        constatns.setShorRun(false);
    }
    
    @RequestMapping(value="/resumeshort",method=RequestMethod.GET)
    public @ResponseBody void resumeShort(){
        quartzService.resumeJob("shortJob", "quartzJobs");
        constatns.setShorRun(true);
    }
    
    @RequestMapping(value="/setshortdelay",method=RequestMethod.GET)
    public @ResponseBody void setShortDelay(@RequestParam("delay") Long delay){
        constatns.setShortUpdatingDelay(delay);
        quartzService.updateShortTrigger(delay);
    }
    
    @RequestMapping(value="/stopglobal",method=RequestMethod.GET)
    public @ResponseBody void stopGlobal(){
        quartzService.pauseJob("globalJob", "quartzJobs");
        constatns.setGlobalRun(false);
    }
    
    @RequestMapping(value="/resumeglobal",method=RequestMethod.GET)
    public @ResponseBody void resumeGlobal(){
        quartzService.resumeJob("globalJob", "quartzJobs");
        constatns.setGlobalRun(true);
    }
    
    @RequestMapping(value="/setglobaldelay",method=RequestMethod.GET)
    public @ResponseBody void setGlobalDelay(@RequestParam("delay") Integer delay){
        constatns.setGlobalUpdatingDelay(delayUtils.getMachineData(delay));
        quartzService.updateGlobalTrigger(delayUtils.getMachineData(delay));
    }
}
