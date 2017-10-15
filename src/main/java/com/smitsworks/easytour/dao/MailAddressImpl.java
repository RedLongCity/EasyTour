package com.smitsworks.easytour.dao;

import com.smitsworks.easytour.models.MailAddress;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity
 * class for access to data base
 */

@Repository("mailAddressDao")
public class MailAddressImpl extends AbstractDao<Integer,MailAddress> implements MailAddressDao{

    @Override
    public List<MailAddress> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<MailAddress> list = crit.list();
        return list;
    }

    @Override
    public MailAddress findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void saveMailAddress(MailAddress address) {
        persist(address);
    }

    @Override
    public void mergeMailAddress(MailAddress address) {
        merge(address);
    }

    @Override
    public void deleteMailAddress(MailAddress address) {
        delete(address);
    }
    
}
