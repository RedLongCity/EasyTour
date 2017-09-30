package com.smitsworks.easytour.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import com.smitsworks.easytour.JsonView.CountryView;
import com.smitsworks.easytour.JsonView.From_CitiesView;
import com.smitsworks.easytour.JsonView.Hotel_RatingView;
import com.smitsworks.easytour.JsonView.RequestView;
import com.smitsworks.easytour.JsonView.RequsetPullElementView;
import com.smitsworks.easytour.JsonView.TourView;
import com.smitsworks.easytour.JsonView.UpdateSessionView;
import com.smitsworks.easytour.models.Country;
import com.smitsworks.easytour.models.Currency;
import com.smitsworks.easytour.models.FiltersResponse;
import com.smitsworks.easytour.models.From_Cities;
import com.smitsworks.easytour.models.Hotel_Rating;
import com.smitsworks.easytour.models.Meal_Type;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.RequestPullElement;
import com.smitsworks.easytour.models.Response;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.models.UpdateSession;
import com.smitsworks.easytour.quartz.services.QuartzService;
import com.smitsworks.easytour.requesthandlers.ItToursHotFiltersRequestHandler;
import com.smitsworks.easytour.requesthandlers.ItToursHotSearchRequestHandler;
import com.smitsworks.easytour.responsecommands.ResponseCommand;
import com.smitsworks.easytour.responsehandlers.ItToursHotFiltersResponseHandler;
import com.smitsworks.easytour.responsehandlers.ItToursHotSearchResponseHandler;
import com.smitsworks.easytour.service.CountryService;
import com.smitsworks.easytour.service.CurrencyService;
import com.smitsworks.easytour.service.From_CitiesService;
import com.smitsworks.easytour.service.Hotel_RatingService;
import com.smitsworks.easytour.service.Meal_TypeService;
import com.smitsworks.easytour.service.RequestPullElementService;
import com.smitsworks.easytour.service.RequestService;
import com.smitsworks.easytour.service.TourService;
import com.smitsworks.easytour.service.UpdateSessionService;
import com.smitsworks.easytour.singletons.ProjectConsantsSingletone;
import com.smitsworks.easytour.utils.GlobalUpdateDelayUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author redlongcity
 */

@RestController
@RequestMapping("/json")
public class JSONController {
    
    @Autowired
    CountryService countryService;
    
    @Autowired
    From_CitiesService from_CitiesService;
    
    @Autowired
    Hotel_RatingService hotel_RatingService;
    
    @Autowired
    Meal_TypeService meal_TypeService;
    
    @Autowired
    CurrencyService currencyService;
    
    @Autowired
    TourService tourService;
    
    @Autowired
    QuartzService quartzService;
    
    @Autowired
    RequestService requestService;
    
    @Autowired
    RequestPullElementService elementService;
    
    @Autowired
    UpdateSessionService sessionService;
    
    @Autowired
    ProjectConsantsSingletone constants;
    
    @Autowired
    GlobalUpdateDelayUtils delayUtils;
    
    @Autowired
    ItToursHotSearchRequestHandler searchRequestHandler;
    
    @Autowired
    ItToursHotSearchResponseHandler searchResponseHandler;
    
    @Autowired
    ItToursHotFiltersRequestHandler filtersRequestHandler;
    
    @Autowired
    ItToursHotFiltersResponseHandler filtersResponseHandler;
            
    @RequestMapping(value="/do",method=RequestMethod.GET)
    public void doSomething(){
        sessionService.deleteAllUpdateSessions();
    }
    
    @RequestMapping(value="/anotherdo",method=RequestMethod.GET)
    public void doSomethingAnother(){
        Request request = new Request();
        request.setNight_From(2);
        request.setNight_Till(4);
        request.setHotel_Rating("3:78");
        searchRequestHandler.handleRequest(request);
    }
    
    @RequestMapping(value="/getshortstatus",method=RequestMethod.GET)
    public ResponseEntity<Boolean> getShortStatus(){
        Boolean status = constants.isShortRun();
        return new ResponseEntity<Boolean>(status,HttpStatus.OK);
    }
    
    @RequestMapping(value="/getglobalstatus",method=RequestMethod.GET)
    public ResponseEntity<Boolean> getGlobalStatus(){
      Boolean status = constants.isGlobalRun();
      return new ResponseEntity<Boolean>(status,HttpStatus.OK);
    }
    
    @RequestMapping(value="/getshortsuspended",method=RequestMethod.GET)
    public ResponseEntity<Boolean> getShortSuspended(){
        return new ResponseEntity<Boolean>(constants.isShortSuspended(),HttpStatus.OK);
    }
    
    @RequestMapping(value="/getglobalsuspended",method=RequestMethod.GET)
    public ResponseEntity<Boolean> getGlobalSuspended(){
        return new ResponseEntity<Boolean>(constants.isGlobalSuspended(),HttpStatus.OK);
    }
    
    @RequestMapping(value="/getshortdelay",method=RequestMethod.GET)
    public ResponseEntity<Long> getShortDelay(){
        Long delay = constants.getShortUpdatingDelay();
        return new ResponseEntity<Long>(delay,HttpStatus.OK);
    }
    
    @RequestMapping(value="/getglobaldelay",method=RequestMethod.GET)
    public ResponseEntity<Integer> getGlobalDelay(){
        Integer delay = delayUtils.getHumanData(constants.getGlobalUpdatingDelay());
        return new ResponseEntity<Integer>(delay,HttpStatus.OK);
    }
    
    @JsonView(CountryView.class)
    @RequestMapping(value="/getfilters", method=RequestMethod.GET)
    public ResponseEntity<FiltersResponse> getFilters(){
        ResponseCommand command = filtersRequestHandler.handleRequest(new Request());
        FiltersResponse response = filtersResponseHandler.executeResponseCommand(command);
        if(response==null){
            return new ResponseEntity<FiltersResponse>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<FiltersResponse>(response,HttpStatus.OK);
    }
    
    @JsonView(TourView.class)
    @RequestMapping(value="/gettours", method=RequestMethod.GET)
    public ResponseEntity<Response> getTours(
            @RequestParam(value="country",required=false) String country_Id,
            @RequestParam(value="from_city",required=false) String from_Cities_Id,
            @RequestParam("hotel_rating") String hotel_Rating,
            @RequestParam("night_from") Integer nightFrom,
            @RequestParam("night_till") Integer nightTill,
            @RequestParam(value="meal_type",required=false) String meal_Type_Id
            ){
        Request request = new Request();
        
        if(country_Id!=null){
        Country country = countryService.findById(country_Id);
        request.setCountry(country);
        }
        
        if(from_Cities_Id!=null){
        From_Cities from_Citites = from_CitiesService.findById(from_Cities_Id);
        request.setFrom_Cities(from_Citites);
        }
        
        if(hotel_Rating==null){
            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
        }
        request.setHotel_Rating(hotel_Rating);
        
        if(nightFrom==null){
            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
        }
        request.setNight_From(nightFrom);
        
        if(nightTill==null){
            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
        }
        request.setNight_Till(nightTill);
        
        if(meal_Type_Id!=null){
            Meal_Type meal_Type = meal_TypeService.findById(meal_Type_Id);
            request.setMeal_Type(meal_Type);
        }
        
        ResponseCommand command = searchRequestHandler.handleRequest(request);
        Response response = searchResponseHandler.executeResponseCommand(command);
        if(response==null){
            return new ResponseEntity<Response>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
    
    @JsonView(TourView.class)
    @RequestMapping(value="/tour",method=RequestMethod.GET)
    public ResponseEntity<List<Tour>> getAllTours(){
    List<Tour> tourList = tourService.findAll();
    if(tourList==null){
        return new ResponseEntity<List<Tour>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Tour>>(tourList,HttpStatus.OK);
}
    @JsonView(TourView.class)
    @RequestMapping(value="/tour/{id}",method=RequestMethod.GET)
    public ResponseEntity<Tour> getTour(@PathVariable("id") Integer id){
        Tour tour = tourService.findById(id);
        if(tour==null){
            return new ResponseEntity<Tour>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Tour>(tour,HttpStatus.OK);
    }
    
    @RequestMapping(value="/tour",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllTours(){
        tourService.deleteAllTours();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/tour/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTourById(@PathVariable("id") Integer id){
        Tour tour = tourService.findById(id);
        if(tour==null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        tourService.deleteTour(tour);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/deletetoursbeforedate/{date}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteToursBerforeDate(@PathVariable("date") Integer date){
        tourService.deleteToursBeforeDate(date);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/deletetoursbetweendates",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteToursBetweenDates(
            @RequestParam("datefrom") Integer dateFrom,
            @RequestParam("datetill") Integer dateTill){
        tourService.deleteToursBetweenDats(dateFrom, dateTill);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(CountryView.class)
    @RequestMapping(value="/country",method=RequestMethod.GET)
    public ResponseEntity<List<Country>> getCountries(){
        List<Country> countryList = countryService.findAll();
        if(countryList==null){
            return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Country>>(countryList,HttpStatus.OK);
    }
    
    @JsonView(CountryView.class)
    @RequestMapping(value="/country/{id}",method=RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@PathVariable("id") String id){
        Country country = countryService.findById(id);
        if(country==null){
            return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Country>(country,HttpStatus.OK);
    }
    
    @RequestMapping(value="/country",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllCountry(){
        countryService.deleteAllCountries();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/country/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCountryById(
                        @PathVariable("id") String id){
        Country country = countryService.findById(id);
        if(country==null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        countryService.deleteCountry(country);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    
    @JsonView(From_CitiesView.class)
    @RequestMapping(value="/city",method=RequestMethod.GET)
    public ResponseEntity<List<From_Cities>> getFrom_CitiesForFilter(){
        List<From_Cities> from_CitiesList = from_CitiesService.findAll();
        if(from_CitiesList==null){
            return new ResponseEntity<List<From_Cities>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<From_Cities>>(from_CitiesList,HttpStatus.OK);
    } 
    
    @JsonView(From_CitiesView.class)
    @RequestMapping(value="city/{id}",method=RequestMethod.GET)
    public ResponseEntity<From_Cities> getCity(@PathVariable("id") String id){
        From_Cities city = from_CitiesService.findById(id);
        if(city==null){
            return new ResponseEntity<From_Cities>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<From_Cities>(city,HttpStatus.OK);
    }
    
    @RequestMapping(value="/city",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllFrom_Cities(){
        from_CitiesService.deleteAllFrom_Cities();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/city/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFrom_CititesById(
                @PathVariable("id") String id){
        From_Cities city = from_CitiesService.findById(id);
        if(city==null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        from_CitiesService.deleteFrom_Cities(city);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(Hotel_RatingView.class)
    @RequestMapping(value="/hotelrating",method=RequestMethod.GET)
    public ResponseEntity<List<Hotel_Rating>> getHotelRatings(){
        List<Hotel_Rating> hotel_RatingList = hotel_RatingService.findAll();
        if(hotel_RatingList==null){
            return new ResponseEntity<List<Hotel_Rating>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Hotel_Rating>>(hotel_RatingList,HttpStatus.OK);
    }
    
    @JsonView(Hotel_RatingView.class)
    @RequestMapping(value="/hotelrating/{id}",method=RequestMethod.GET)
    public ResponseEntity<Hotel_Rating> getHotelRating(
            @PathVariable("id") String id){
        Hotel_Rating rating = hotel_RatingService.findById(id);
        if(rating==null){
            return new ResponseEntity<Hotel_Rating>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hotel_Rating>(rating,HttpStatus.OK);
    }
    
    @RequestMapping(value="/hotelrating",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllHotelRatings(){
        hotel_RatingService.deleteAllHotel_Rating();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/hotelrating/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteHotelRatingById(
                @PathVariable("id") String id){
        Hotel_Rating rating = hotel_RatingService.findById(id);
        if(rating==null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        hotel_RatingService.deleteHotel_Rating(rating);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(TourView.class)
    @RequestMapping(value="/mealtype",method=RequestMethod.GET)
    public ResponseEntity<List<Meal_Type>> getMealTypes(){
       List<Meal_Type> meal_TypeList = meal_TypeService.findAll();
       if(meal_TypeList==null){
           return new ResponseEntity<List<Meal_Type>>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<List<Meal_Type>>(meal_TypeList,HttpStatus.OK);
    }
    
    @JsonView(TourView.class)
    @RequestMapping(value="/mealtype/{id}",method=RequestMethod.GET)
    public ResponseEntity<Meal_Type> getMealType(@PathVariable("id") String id){
        Meal_Type mealType = meal_TypeService.findById(id);
        if(mealType==null){
            return new ResponseEntity<Meal_Type>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Meal_Type>(mealType,HttpStatus.OK);
    }
    
    @RequestMapping(value="/mealtype",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllMealTypes(){
        meal_TypeService.deleteAllMeal_Type();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/mealtype/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMealTypeById(
                @PathVariable("id") String id){
        Meal_Type type = meal_TypeService.findById(id);
        if(type==null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        meal_TypeService.deleteMeal_Type(type);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(TourView.class)
    @RequestMapping(value="/currency",method=RequestMethod.GET)
    public ResponseEntity<List<Currency>> getCurrencies(){
        List<Currency> currencyList = currencyService.findAll();
        if(currencyList==null){
            return new ResponseEntity<List<Currency>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Currency>>(currencyList,HttpStatus.OK);
    }
    
    @JsonView(TourView.class)
    @RequestMapping(value="/currency/{id}",method=RequestMethod.GET)
    public ResponseEntity<Currency> getCurrency(@PathVariable("id") String id){
        Currency currency = currencyService.findById(id);
        if(currency==null){
            return new ResponseEntity<Currency>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Currency>(currency,HttpStatus.OK);
    }
    
    @RequestMapping(value="/currency",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllCurrencies(){
        currencyService.deleteAllCurrency();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/currency/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCurrencyById(
                    @PathVariable("id") String id){
        Currency currency = currencyService.findById(id);
        if(currency==null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        currencyService.deleteCurrency(currency);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(RequsetPullElementView.class)
    @RequestMapping(value="/element",method=RequestMethod.GET)
    public ResponseEntity<List<RequestPullElement>> getPullElements(){
        List<RequestPullElement> elementList = elementService.findAll();
        if(elementList==null){
            return new ResponseEntity<List<RequestPullElement>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RequestPullElement>>(elementList,HttpStatus.OK);
    }

    @JsonView(RequsetPullElementView.class)
    @RequestMapping(value="/element/{id}",method=RequestMethod.GET)
    public ResponseEntity<RequestPullElement> getPullElement(
            @PathVariable("id") Integer id){
       RequestPullElement element = elementService.findById(id);
       if(element==null){
           return new ResponseEntity<RequestPullElement>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<RequestPullElement>(element,HttpStatus.OK);
    }
    
    @JsonView(RequsetPullElementView.class)
    @RequestMapping(value="/getelements",method=RequestMethod.GET)
    public ResponseEntity<List<RequestPullElement>> getElementsByDate(
            @RequestParam("datefrom") Long dateFrom,
            @RequestParam("datetill") Long dateTill){
        Timestamp from = new Timestamp(dateFrom);
        Timestamp till = new Timestamp(dateTill);
        List<RequestPullElement> elementsList = elementService.findByDateInterval(from,till);
        if(elementsList==null){
            return new ResponseEntity<List<RequestPullElement>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RequestPullElement>>(elementsList,HttpStatus.OK);
    }
    
    @RequestMapping(value="/element",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllElements(){
        elementService.deleteAllRequestPullElements();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/element/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteElementById(@PathVariable("id") Integer id){
      RequestPullElement element = elementService.findById(id);
      if(element==null){
          return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
      }
      elementService.deleteRequestPullElement(element);
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/deleteelementsbeforedate/{date}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteElementBeforeDate(
            @PathVariable("date") Long date){
        Timestamp dateBefore = new Timestamp(date);
        elementService.deleteElementsBeforeDate(dateBefore);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/deleteelementsbetweendates",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteElementBetweenDates(
            @RequestParam("datefrom") Long dateFrom,
            @RequestParam("datetill") Long dateTill){
        Timestamp from = new Timestamp(dateFrom);
        Timestamp till = new Timestamp(dateTill);
        elementService.deleteElementsBetweenDates(from, till);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(UpdateSessionView.class)
    @RequestMapping(value="/session",method=RequestMethod.GET)
    public ResponseEntity<List<UpdateSession>> getSessions(){
        List<UpdateSession> sessionList = sessionService.findAll();
        if(sessionService==null){
            return new ResponseEntity<List<UpdateSession>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UpdateSession>>(sessionList,HttpStatus.OK);
    }

    @JsonView(UpdateSessionView.class)
    @RequestMapping(value="/session/{id}",method=RequestMethod.GET)
    public ResponseEntity<UpdateSession> getSession(
            @PathVariable("id") Integer id){
        UpdateSession session = sessionService.findById(id);
        if(session==null){
            return new ResponseEntity<UpdateSession>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UpdateSession>(session,HttpStatus.OK);
    }
    
    @RequestMapping(value="/session",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllSessions(){
        sessionService.deleteAllUpdateSessions();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
    }
    
    @RequestMapping(value="/session/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSessionById(@PathVariable("id") Integer id){
        UpdateSession session = sessionService.findById(id);
        if(session==null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        sessionService.deleteUpdateSession(session);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/deletesessionsbeforedate/{date}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSessionsBeforeDate(
                    @PathVariable("date") Long date){
        Timestamp dateBefore = new Timestamp(date);
        sessionService.deleteSessionsBeforeDate(dateBefore);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/deletesessionsbetweendates",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSessionsBetweenDates(
                    @RequestParam("datefrom") Long dateFrom,
                    @RequestParam("datetill") Long dateTill){
        Timestamp from = new Timestamp(dateFrom);
        Timestamp till = new Timestamp(dateTill);
        sessionService.deleteSessionsBetweenDates(from, till);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(UpdateSessionView.class)
    @RequestMapping(value="/getsessions",method=RequestMethod.GET)
    public ResponseEntity<List<UpdateSession>> getSessionsByDates(
            @RequestParam("datefrom") Long dateFrom,
            @RequestParam("datetill") Long dateTill){
        Timestamp from = new Timestamp(dateFrom);
        Timestamp till = new Timestamp(dateTill);
        List<UpdateSession> sessionList=sessionService.findByDates(from, till);
        if(sessionList==null){
            return new ResponseEntity<List<UpdateSession>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UpdateSession>>(sessionList,HttpStatus.OK);
    }

    
    @JsonView(RequestView.class)
    @RequestMapping(value="/request",method=RequestMethod.GET)
    public ResponseEntity<List<Request>> getRequests(){
        List<Request> requestList = requestService.findAll();
        if(requestList==null){
            return new ResponseEntity<List<Request>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Request>>(requestList,HttpStatus.OK);
    }

    @JsonView(RequestView.class)
    @RequestMapping(value="/request/{id}",method=RequestMethod.GET)
    public ResponseEntity<Request> getRequest(@PathVariable("id") Integer id){
        Request request = requestService.findById(id);
        if(request==null){
            return new ResponseEntity<Request>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Request>(request,HttpStatus.OK);
    }
    
    @RequestMapping(value="/request",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllRequests(){
        requestService.deleteAllRequests();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/request/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRequestById(@PathVariable("id") Integer id){
        Request request = requestService.findById(id);
        if(request==null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        requestService.deleteRequest(request);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
}