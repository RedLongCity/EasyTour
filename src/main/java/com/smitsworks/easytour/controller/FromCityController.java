package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.json.view.From_CitiesView;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.service.From_CitiesService;
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
public class FromCityController {

    @Autowired
    From_CitiesService service;

    @JsonView(From_CitiesView.class)
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public ResponseEntity<List<From_Cities>> getFrom_CitiesForFilter() {
        List<From_Cities> from_CitiesList = service.findAll();
        if (from_CitiesList == null) {
            return new ResponseEntity<List<From_Cities>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<From_Cities>>(from_CitiesList, HttpStatus.OK);
    }

    @JsonView(From_CitiesView.class)
    @RequestMapping(value = "city/{id}", method = RequestMethod.GET)
    public ResponseEntity<From_Cities> getCity(@PathVariable("id") String id) {
        From_Cities city = service.findById(id);
        if (city == null) {
            return new ResponseEntity<From_Cities>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<From_Cities>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "/city", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllFrom_Cities() {
        service.deleteAllFrom_Cities();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFrom_CititesById(
            @PathVariable("id") String id) {
        From_Cities city = service.findById(id);
        if (city == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteFrom_Cities(city);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
