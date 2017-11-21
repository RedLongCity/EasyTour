package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.json.view.RequestView;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.service.RequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity
 * 21/11/2017
 */
@RestController
@RequestMapping("/json")
public class RequestController {

    @Autowired
    private RequestService service;

    @JsonView(RequestView.class)
    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public ResponseEntity<List<Request>> getRequests() {
        List<Request> requestList = service.findAll();
        if (requestList == null) {
            return new ResponseEntity<List<Request>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Request>>(requestList, HttpStatus.OK);
    }

    @JsonView(RequestView.class)
    @RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
    public ResponseEntity<Request> getRequest(@PathVariable("id") Integer id) {
        Request request = service.findById(id);
        if (request == null) {
            return new ResponseEntity<Request>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Request>(request, HttpStatus.OK);
    }

    @RequestMapping(value = "/request", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllRequests() {
        service.deleteAllRequests();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/request/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRequestById(@PathVariable("id") Integer id) {
        Request request = service.findById(id);
        if (request == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteRequest(request);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
