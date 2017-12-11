package com.smitsworks.easytour.utils;

import com.smitsworks.easytour.models.UpdateSession;
import com.smitsworks.easytour.service.UpdateSessionService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
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

    public Timestamp getCurrentTime() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp time = new Timestamp(cal.getTimeInMillis());
        return time;
    }

    public UpdateSession getCurrentSession() {
        UpdateSession session = sessionService.findByUpdateTime(
                projectConsantsSingletone.getTimeOfCurrentSession());
        return session;
    }

    public void updateTimeConstants() {
        projectConsantsSingletone.setTimeOfCurrentSession(getCurrentTime());
    }
}
