package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.UpdateSessionDao;
import com.smitsworks.easytour.models.UpdateSession;
import com.smitsworks.easytour.utils.TimeUtils;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity
 * 13.09.2017
 * service layer of dao operations
 */
@Service("updateSessionService")
@Transactional
public class UpdateSessionServiceImpl implements  UpdateSessionService{

    @Autowired
    UpdateSessionDao sessionDao;
    
    @Autowired
    TimeUtils utils;
    
    @Override
    public UpdateSession findById(Integer id) {
       return sessionDao.findById(id);
    }

    @Override
    public List<UpdateSession> findByDates(Timestamp dateFrom, Timestamp dateTill) {
        return sessionDao.findByDates(dateFrom, dateTill);
    }

    
    
    @Override
    public UpdateSession findByUpdateTime(Timestamp updateTime) {
        return sessionDao.findsessionByTime(updateTime);
    }

    @Override
    public UpdateSession getPreviousSession() {
        return sessionDao.findsessionByTime(utils.getTimeOfPreviousSession());
    }

    @Override
    public void saveUpdateSession(UpdateSession session) {
        sessionDao.saveUpdateSession(session);
    }

    @Override
    public void updateUpdateSession(UpdateSession session) {
        UpdateSession entity = sessionDao.findById(session.getId());
        if(entity!=null){
            entity.setSessionTime(session.getSessionTime());
            entity.setRequestPullElementSet(session.getRequestPullElementSet());
            sessionDao.mergeUpdateSession(entity);
        }
    }

    @Override
    public void deleteUpdateSession(UpdateSession session) {
        sessionDao.deleteUpdateSeesion(session);
    }

    @Override
    public void deleteSessionsBeforeDate(Timestamp date) {
        List<UpdateSession> sessionsList = sessionDao.findBeforeDate(date);
        if(sessionsList!=null){
            for(UpdateSession session:sessionsList){
                sessionDao.deleteUpdateSeesion(session);
            }
        }
    }

    @Override
    public void deleteSessionsBetweenDates(Timestamp dateFrom, Timestamp dateTill) {
        List<UpdateSession> sessionsList = 
                sessionDao.findByDates(dateFrom, dateTill);
        if(sessionsList!=null){
            for(UpdateSession session:sessionsList){
                sessionDao.deleteUpdateSeesion(session);
            }
        }
     }
    
    

    @Override
    public List<UpdateSession> findAll() {
        return sessionDao.findAll();
    }

    @Override
    public void deleteAllUpdateSessions() {
        List<UpdateSession> sessionList = sessionDao.findAll();
        if(sessionList!=null){
            for(UpdateSession session:sessionList){
                sessionDao.deleteUpdateSeesion(session);
            }
        }
    }
    
}
