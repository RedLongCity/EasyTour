/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.RequestPullElement;
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
 * 08.09.2017
 * class for Dao manipulating directly
 */
@Repository("requestPullElementDao")
public class RequestPullElementDaoImpl extends AbstractDao<Integer, RequestPullElement>
        implements RequestPullElementDao {

    @Override
    public List<RequestPullElement> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("request_pull_DateTime"));
        List<RequestPullElement> requestPullElementList = crit.list();
        if (requestPullElementList != null) {
            for (RequestPullElement requestPullElement : requestPullElementList) {
                Hibernate.initialize(requestPullElement.getRequest());
                Hibernate.initialize(requestPullElement.getUpdateSession());
            }
        }
        return requestPullElementList;
    }

    @Override
    public List<RequestPullElement> findByDate(Timestamp request_DateTime) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("request_pull_DateTime", request_DateTime));
        List<RequestPullElement> requestPullElementList = crit.list();
        if (requestPullElementList != null) {
            for (RequestPullElement requestPullElement : requestPullElementList) {
                Hibernate.initialize(requestPullElement.getRequest());
                Hibernate.initialize(requestPullElement.getUpdateSession());
            }
        }
        return requestPullElementList;
    }

    @Override
    public List<RequestPullElement> findByDatesInterval(Timestamp dateFrom, Timestamp dateTill) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.le("request_pull_DateTime", dateTill));
        crit.add(Restrictions.ge("request_pull_DateTime", dateFrom));
        List<RequestPullElement> requestPullElementList = crit.list();
        if (requestPullElementList != null) {
            for (RequestPullElement requestPullElement : requestPullElementList) {
                Hibernate.initialize(requestPullElement.getRequest());
                Hibernate.initialize(requestPullElement.getUpdateSession());
            }
        }
        return requestPullElementList;
    }

    @Override
    public List<RequestPullElement> findBeforeDate(Timestamp date) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.le("request_pull_DateTime", date));
        List<RequestPullElement> elementsList = crit.list();
        if (elementsList != null) {
            for (RequestPullElement requestPullElement : elementsList) {
                Hibernate.initialize(requestPullElement.getRequest());
                Hibernate.initialize(requestPullElement.getUpdateSession());
            }
        }
        return elementsList;
    }

    @Override
    public RequestPullElement findById(Integer id) {
        RequestPullElement requestPullElement = getByKey(id);
        if (requestPullElement != null) {
            Hibernate.initialize(requestPullElement.getRequest());
            Hibernate.initialize(requestPullElement.getUpdateSession());
        }
        return requestPullElement;
    }

    @Override
    public void saveRequestPullElement(RequestPullElement requestPullElement) {
        persist(requestPullElement);
    }

    @Override
    public void mergeRequestPullElement(RequestPullElement requestPullElement) {
        merge(requestPullElement);
    }

    @Override
    public void deleteRequestPullElement(RequestPullElement requestPullElement) {
        delete(requestPullElement);
    }

}
