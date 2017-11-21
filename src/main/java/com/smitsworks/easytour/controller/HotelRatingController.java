package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.json.view.Hotel_RatingView;
import com.smitsworks.easytour.models.Hotel_Rating;
import com.smitsworks.easytour.service.Hotel_RatingService;
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
public class HotelRatingController {

    @Autowired
    private Hotel_RatingService service;

    @JsonView(Hotel_RatingView.class)
    @RequestMapping(value = "/hotelrating", method = RequestMethod.GET)
    public ResponseEntity<List<Hotel_Rating>> getHotelRatings() {
        List<Hotel_Rating> hotel_RatingList = service.findAll();
        if (hotel_RatingList == null) {
            return new ResponseEntity<List<Hotel_Rating>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Hotel_Rating>>(hotel_RatingList, HttpStatus.OK);
    }

    @JsonView(Hotel_RatingView.class)
    @RequestMapping(value = "/hotelrating/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hotel_Rating> getHotelRating(
            @PathVariable("id") String id) {
        Hotel_Rating rating = service.findById(id);
        if (rating == null) {
            return new ResponseEntity<Hotel_Rating>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hotel_Rating>(rating, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotelrating", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllHotelRatings() {
        service.deleteAllHotel_Rating();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/hotelrating/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteHotelRatingById(
            @PathVariable("id") String id) {
        Hotel_Rating rating = service.findById(id);
        if (rating == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteHotel_Rating(rating);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
