package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.json.view.UpdateSessionView;
import com.smitsworks.easytour.models.UpdateSession;
import com.smitsworks.easytour.service.UpdateSessionService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity
 * 21/11/2017
 */
@RestController
@RequestMapping("/json")
public class SessionController {

    @Autowired
    private UpdateSessionService service;

    @JsonView(UpdateSessionView.class)
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public ResponseEntity<List<UpdateSession>> getSessions() {
        List<UpdateSession> sessionList = service.findAll();
        if (service == null) {
            return new ResponseEntity<List<UpdateSession>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UpdateSession>>(sessionList, HttpStatus.OK);
    }

    @JsonView(UpdateSessionView.class)
    @RequestMapping(value = "/session/{id}", method = RequestMethod.GET)
    public ResponseEntity<UpdateSession> getSession(
            @PathVariable("id") Integer id) {
        UpdateSession session = service.findById(id);
        if (session == null) {
            return new ResponseEntity<UpdateSession>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UpdateSession>(session, HttpStatus.OK);
    }

    @RequestMapping(value = "/session", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllSessions() {
        service.deleteAllUpdateSessions();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/session/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSessionById(@PathVariable("id") Integer id) {
        UpdateSession session = service.findById(id);
        if (session == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteUpdateSession(session);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/deletesessionsbeforedate/{date}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSessionsBeforeDate(
            @PathVariable("date") Long date) {
        Timestamp dateBefore = new Timestamp(date);
        service.deleteSessionsBeforeDate(dateBefore);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/deletesessionsbetweendates", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSessionsBetweenDates(
            @RequestParam("datefrom") Long dateFrom,
            @RequestParam("datetill") Long dateTill) {
        Timestamp from = new Timestamp(dateFrom);
        Timestamp till = new Timestamp(dateTill);
        service.deleteSessionsBetweenDates(from, till);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @JsonView(UpdateSessionView.class)
    @RequestMapping(value = "/getsessions", method = RequestMethod.GET)
    public ResponseEntity<List<UpdateSession>> getSessionsByDates(
            @RequestParam("datefrom") Long dateFrom,
            @RequestParam("datetill") Long dateTill) {
        Timestamp from = new Timestamp(dateFrom);
        Timestamp till = new Timestamp(dateTill);
        List<UpdateSession> sessionList = service.findByDates(from, till);
        if (sessionList == null) {
            return new ResponseEntity<List<UpdateSession>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UpdateSession>>(sessionList, HttpStatus.OK);
    }

}
