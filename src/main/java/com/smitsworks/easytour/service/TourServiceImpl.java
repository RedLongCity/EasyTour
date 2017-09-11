package com.smitsworks.easytour.service;

import com.smitsworks.easytour.dao.TourDao;
import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.models.Tour;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */

@Service("tourService")
@Transactional
public class TourServiceImpl implements TourService{

    @Autowired
    TourDao tourDao;
    
    @Override
    public Tour findById(Integer id) {
        return tourDao.findById(id);
    }

    @Override
    public Tour findByRequest(Request request) {
        return tourDao.findByRequest(request);
    }

    @Override
    public Tour findByKey(String key) {
        return tourDao.findByKey(key);
    }
    
    @Override
    public void saveTour(Tour tour) {
        tourDao.save(tour);
    }

    @Override
    public void updateTour(Tour tour) {
        Tour entity = tourDao.findById(tour.getId());
        if(entity!=null){
            entity.setKey(tour.getKey());
            entity.setType(tour.getType());
            entity.setCountry(tour.getCountry());
            entity.setRegion(tour.getRegion());
            entity.setHotel_id(tour.getHotel_id());
            entity.setHotel(tour.getHotel());
            entity.setHotel_Rating(tour.getHotel_Rating());
            entity.setMeal_Type(tour.getMeal_Type());
            entity.setRoom_Type(tour.getRoom_Type());
            entity.setAdult_Amount(tour.getAdult_Amount());
            entity.setChild_Amount(tour.getChild_Amount());
            entity.setAccomodation(tour.getAccomodation());
            entity.setDuration(tour.getDuration());
            entity.setDate_From(tour.getDate_From());
            entity.setDate_From_Unix(tour.getDate_From_Unix());
            entity.setCurrency_id(tour.getCurrency_id());
            entity.setCurrency_Symbol(tour.getCurrency_Symbol());
            entity.setPrices(tour.getPrices());
            entity.setPrice_Old(tour.getPrice_Old());
            entity.setPrice_Change_Percent(tour.getPrice_Change_Percent());
            entity.setFrom_Cities(tour.getFrom_Cities());
            entity.setFrom_City_Gen(tour.getFrom_City_Gen());
            entity.setTransport_Type(tour.getTransport_Type());
            entity.setHotel_ImageSet(tour.getHotel_ImageSet());
            entity.setRequestSet(tour.getRequestSet());
            tourDao.mergeTour(entity);
        }
    }

    @Override
    public void deleteTour(Tour tour) {
        tourDao.deleteTour(tour);
    }

    @Override
    public List<Tour> findAll() {
        return tourDao.findAll();
    }

    @Override
    public List<Tour> findToursByRequest(Request request) {
        return tourDao.getToursByRequest(request);
    }

    @Override
    public void deleteAllTours() {
        List<Tour> tourList = tourDao.findAll();
        if(tourList!=null){
            for(Tour tour:tourList){
                tourDao.deleteTour(tour);
            }
        }
    }
    
}
