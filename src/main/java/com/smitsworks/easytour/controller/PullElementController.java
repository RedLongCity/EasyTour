package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.json.view.RequsetPullElementView;
import com.smitsworks.easytour.models.RequestPullElement;
import com.smitsworks.easytour.service.RequestPullElementService;
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
public class PullElementController {

    @Autowired
    private RequestPullElementService service;

    @JsonView(RequsetPullElementView.class)
    @RequestMapping(value = "/element", method = RequestMethod.GET)
    public ResponseEntity<List<RequestPullElement>> getPullElements() {
        List<RequestPullElement> elementList = service.findAll();
        if (elementList == null) {
            return new ResponseEntity<List<RequestPullElement>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RequestPullElement>>(elementList, HttpStatus.OK);
    }

    @JsonView(RequsetPullElementView.class)
    @RequestMapping(value = "/element/{id}", method = RequestMethod.GET)
    public ResponseEntity<RequestPullElement> getPullElement(
            @PathVariable("id") Integer id) {
        RequestPullElement element = service.findById(id);
        if (element == null) {
            return new ResponseEntity<RequestPullElement>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<RequestPullElement>(element, HttpStatus.OK);
    }

    @JsonView(RequsetPullElementView.class)
    @RequestMapping(value = "/getelements", method = RequestMethod.GET)
    public ResponseEntity<List<RequestPullElement>> getElementsByDate(
            @RequestParam("datefrom") Long dateFrom,
            @RequestParam("datetill") Long dateTill) {
        Timestamp from = new Timestamp(dateFrom);
        Timestamp till = new Timestamp(dateTill);
        List<RequestPullElement> elementsList = service.findByDateInterval(from, till);
        if (elementsList == null) {
            return new ResponseEntity<List<RequestPullElement>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RequestPullElement>>(elementsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/element", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllElements() {
        service.deleteAllRequestPullElements();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/element/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteElementById(@PathVariable("id") Integer id) {
        RequestPullElement element = service.findById(id);
        if (element == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteRequestPullElement(element);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/deleteelementsbeforedate/{date}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteElementBeforeDate(
            @PathVariable("date") Long date) {
        Timestamp dateBefore = new Timestamp(date);
        service.deleteElementsBeforeDate(dateBefore);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/deleteelementsbetweendates", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteElementBetweenDates(
            @RequestParam("datefrom") Long dateFrom,
            @RequestParam("datetill") Long dateTill) {
        Timestamp from = new Timestamp(dateFrom);
        Timestamp till = new Timestamp(dateTill);
        service.deleteElementsBetweenDates(from, till);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
