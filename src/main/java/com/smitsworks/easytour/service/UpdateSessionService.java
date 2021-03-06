package com.smitsworks.easytour.service;

import com.smitsworks.easytour.models.UpdateSession;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author redlongcity
 * 13.09.2017
 * service layer of dao operations
 */

public interface UpdateSessionService {
    
    UpdateSession findById(Integer id);
    
    UpdateSession findByUpdateTime(Timestamp updateTime);
    
    List<UpdateSession> findByDates(Timestamp dateFrom,
            Timestamp dateTill);
    
    void saveUpdateSession(UpdateSession session);
    
    void updateUpdateSession(UpdateSession session);//lol... nice name of method dude
    
    void deleteUpdateSession(UpdateSession session);
    
    void deleteSessionsBeforeDate(Timestamp date);
    
    void deleteSessionsBetweenDates(Timestamp dateFrom,
            Timestamp dateTill);
    
    List<UpdateSession> findAll();
    
    void deleteAllUpdateSessions();
    
}
