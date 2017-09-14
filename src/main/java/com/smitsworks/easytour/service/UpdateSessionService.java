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
    
    UpdateSession getPreviousSession();
    
    void saveUpdateSession(UpdateSession session);
    
    void updateUpdateSession(UpdateSession session);//lol... nice name of method dude
    
    void deleteUpdateSession(UpdateSession session);
    
    List<UpdateSession> findAll();
    
    void deleteAllUpdateSessions();
    
}
