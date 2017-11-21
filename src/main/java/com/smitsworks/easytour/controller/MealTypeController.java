package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.json.view.TourView;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.service.Meal_TypeService;
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
public class MealTypeController {

    @Autowired
    private Meal_TypeService service;

    @JsonView(TourView.class)
    @RequestMapping(value = "/mealtype", method = RequestMethod.GET)
    public ResponseEntity<List<Meal_Type>> getMealTypes() {
        List<Meal_Type> meal_TypeList = service.findAll();
        if (meal_TypeList == null) {
            return new ResponseEntity<List<Meal_Type>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Meal_Type>>(meal_TypeList, HttpStatus.OK);
    }

    @JsonView(TourView.class)
    @RequestMapping(value = "/mealtype/{id}", method = RequestMethod.GET)
    public ResponseEntity<Meal_Type> getMealType(@PathVariable("id") String id) {
        Meal_Type mealType = service.findById(id);
        if (mealType == null) {
            return new ResponseEntity<Meal_Type>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Meal_Type>(mealType, HttpStatus.OK);
    }

    @RequestMapping(value = "/mealtype", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllMealTypes() {
        service.deleteAllMeal_Type();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/mealtype/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMealTypeById(
            @PathVariable("id") String id) {
        Meal_Type type = service.findById(id);
        if (type == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.deleteMeal_Type(type);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
