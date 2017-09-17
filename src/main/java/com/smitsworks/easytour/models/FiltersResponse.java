package com.smitsworks.easytour.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.smitsworks.easytour.JsonView.TourView;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author redlongcity
 * 15/09/2017
 * class for incapsulating ItTours Hot Filters response to client
 */
public class FiltersResponse {
    
    @JsonView(TourView.class)
    private Long delay;
    
    @JsonView(TourView.class)
    private List<Country> countriesList;
    
    @JsonView(TourView.class)
    private List<From_Cities> from_CititesList;

    @JsonView(TourView.class)
    private List<Hotel_Rating> hotel_RatingList;

    @JsonView(TourView.class)
    private List<Meal_Type> meal_TypeList;

    @JsonView(TourView.class)
    private List<Currency> currencyList;

    public List<Country> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(List<Country> countriesList) {
        this.countriesList = countriesList;
    }

    public List<From_Cities> getFrom_CititesList() {
        return from_CititesList;
    }

    public void setFrom_CititesList(List<From_Cities> from_CititesList) {
        this.from_CititesList = from_CititesList;
    }

    public List<Hotel_Rating> getHotel_RatingList() {
        return hotel_RatingList;
    }

    public void setHotel_RatingList(List<Hotel_Rating> hotel_RatingList) {
        this.hotel_RatingList = hotel_RatingList;
    }

    public List<Meal_Type> getMeal_TypeList() {
        return meal_TypeList;
    }

    public void setMeal_TypeList(List<Meal_Type> meal_TypeList) {
        this.meal_TypeList = meal_TypeList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.delay);
        hash = 97 * hash + Objects.hashCode(this.countriesList);
        hash = 97 * hash + Objects.hashCode(this.from_CititesList);
        hash = 97 * hash + Objects.hashCode(this.hotel_RatingList);
        hash = 97 * hash + Objects.hashCode(this.meal_TypeList);
        hash = 97 * hash + Objects.hashCode(this.currencyList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FiltersResponse other = (FiltersResponse) obj;
        if (!Objects.equals(this.delay, other.delay)) {
            return false;
        }
        if (!Objects.equals(this.countriesList, other.countriesList)) {
            return false;
        }
        if (!Objects.equals(this.from_CititesList, other.from_CititesList)) {
            return false;
        }
        if (!Objects.equals(this.hotel_RatingList, other.hotel_RatingList)) {
            return false;
        }
        if (!Objects.equals(this.meal_TypeList, other.meal_TypeList)) {
            return false;
        }
        if (!Objects.equals(this.currencyList, other.currencyList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FiltersResponse{" + "delay=" + delay + ", countriesList=" + countriesList + ", from_CititesList=" + from_CititesList + ", hotel_RatingList=" + hotel_RatingList + ", meal_TypeList=" + meal_TypeList + ", currencyList=" + currencyList + '}';
    }
    
}
