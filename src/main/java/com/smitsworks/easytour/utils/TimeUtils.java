package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.UpdateSession;
import com.smitsworks.easytour.service.UpdateSessionService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 10.09.2017
 * class for getting data about current time
 */
@Service
public class TimeUtils {
    
    @Autowired
    UpdateSessionService sessionService;
    
    @Autowired
    ProjectConsantsSingletone projectConsantsSingletone;
    
    public Timestamp getCurrentTime(){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time;
    }
    
    public  Timestamp getTimeOfPreviousSession(){
        Timestamp time = projectConsantsSingletone.getTimeOfPreviousSession();
        return time;
    }
    
    public UpdateSession getCurrentSession(){
        UpdateSession session = sessionService.findByUpdateTime(
                projectConsantsSingletone.getTimeOfCurrentSession());
        return session;
    }
    
    public UpdateSession getPreviousSession(){
        UpdateSession session = sessionService.findByUpdateTime(
                projectConsantsSingletone.getTimeOfPreviousSession());
        return session;
    }
    
    public void updateTimeConstants(){
        projectConsantsSingletone.setTimeOfPreviousSession(
                projectConsantsSingletone.getTimeOfCurrentSession());
        projectConsantsSingletone.setTimeOfCurrentSession(getCurrentTime());
    }
}
