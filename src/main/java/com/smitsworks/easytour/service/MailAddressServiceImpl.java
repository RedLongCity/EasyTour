package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.MailAddressDao;
import com.smitsworks.easytour.models.MailAddress;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity
 * class like facade for manipulating with MailAddress's objects
 */

@Service("mailAddressService")
@Transactional
public class MailAddressServiceImpl implements MailAddressService {

    @Autowired
    MailAddressDao dao;
    
    @Override
    public MailAddress findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public void saveMailAddress(MailAddress address) {
        dao.saveMailAddress(address);
    }

    @Override
    public void updateMailAddress(MailAddress address) {
        MailAddress entity = dao.findById(address.getId());
        if(entity!=null){
            entity.setName(address.getName());
            entity.setEmailAddress(address.getEmailAddress());
            dao.mergeMailAddress(entity);
        }
    }

    @Override
    public void deleteMailAddress(MailAddress address) {
        dao.deleteMailAddress(address);
    }

    @Override
    public List<MailAddress> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAllMailAddresses() {
        List<MailAddress> list = dao.findAll();
        if(list!=null){
            for(MailAddress address:list){
                dao.deleteMailAddress(address);
            }
        }
    }
    
}
