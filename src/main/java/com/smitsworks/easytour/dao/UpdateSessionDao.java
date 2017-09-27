package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.UpdateSession;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author redlongcity
 * interface for configuration dao class
 */

public interface UpdateSessionDao {
 
    List<UpdateSession> findAll();
    
    List<UpdateSession> findByDates(Timestamp datefrom,
            Timestamp dateTill);
    
    UpdateSession findById(Integer id);
    
    UpdateSession findsessionByTime(Timestamp updateTime);
    
    void saveUpdateSession(UpdateSession session);
    
    void mergeUpdateSession(UpdateSession session);
    
    void deleteUpdateSeesion(UpdateSession session);
}
