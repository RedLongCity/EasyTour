package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.UpdateSession;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity
 * 13.09.2017
 * class for dao operations with UpdateSession
 */
@Repository("updateSession")
public class UpdateSessionDaoImpl extends AbstractDao<Integer,UpdateSession> implements UpdateSessionDao {

    @Override
    public List<UpdateSession> findAll() {
       Criteria crit = createCriteria(); 
       crit.addOrder(Order.asc("session_time"));
       List<UpdateSession> sessionList = crit.list();
       if(sessionList!=null){
           for(UpdateSession session:sessionList){
               Hibernate.initialize(session.getElementSet());
           }
       }
       return sessionList;
    }

    @Override
    public UpdateSession findById(Integer id) {
        UpdateSession session = getByKey(id);
        if(session!=null){
            Hibernate.initialize(session.getElementSet());
        }
        return session;
    }

    @Override
    public UpdateSession findsessionByTime(Timestamp updateTime) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("session_time",updateTime));
        UpdateSession session = (UpdateSession) crit.uniqueResult();
        if(session!=null){
            Hibernate.initialize(session.getElementSet());

        }
        return session;
    }
    

    @Override
    public void saveUpdateSession(UpdateSession session) {
        persist(session);
    }

    @Override
    public void mergeUpdateSession(UpdateSession session) {
        merge(session);
    }

    @Override
    public void deleteUpdateSeesion(UpdateSession session) {
        delete(session);
    }
    
}
