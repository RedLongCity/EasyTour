package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.models.MailAddress;
import com.smitsworks.easytour.service.MailAddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity
 * 08/11/2017
 * controller for endpoints of MailAddress
 */
@RestController
@RequestMapping("/json")
public class MailAddressController {

    @Autowired
    private MailAddressService service;

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ResponseEntity<List<MailAddress>> getMailAddresss() {
        List<MailAddress> list = service.findAll();
        if (list == null) {
            return new ResponseEntity<List<MailAddress>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MailAddress>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    public ResponseEntity<MailAddress> getMailAddress(@PathVariable("id") Integer id) {
        MailAddress address = service.findById(id);
        if (address == null) {
            return new ResponseEntity<MailAddress>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<MailAddress>(address, HttpStatus.OK);
    }

    @RequestMapping(value = "/address/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMailAddress(@RequestBody MailAddress address) {
        MailAddress entity = service.findById(address.getId());
        if (entity != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        service.saveMailAddress(address);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MailAddress> updateMailAddress(@PathVariable("id") Integer id,
            @RequestBody MailAddress address) {
        MailAddress entity = service.findById(id);
        if (entity == null) {
            return new ResponseEntity<MailAddress>(HttpStatus.NOT_FOUND);
        }
        entity.setName(address.getName());
        service.updateMailAddress(entity);

        return new ResponseEntity<MailAddress>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMailAddress(@PathVariable("id") Integer id) {
        MailAddress address = service.findById(id);
        if (address == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteMailAddress(address);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
