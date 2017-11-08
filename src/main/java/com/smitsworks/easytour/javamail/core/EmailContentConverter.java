package com.smitsworks.easytour.javamail.core;

import com.smitsworks.easytour.models.MailAddress;
import com.smitsworks.easytour.models.Order;
import com.smitsworks.easytour.models.Price;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.models.UserData;
import com.smitsworks.easytour.service.MailAddressService;
import com.smitsworks.easytour.service.TourService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity class for converting Order object to Email content
 */
@Service
public class EmailContentConverter {

    @Autowired
    TourService tourService;

    @Autowired
    MailAddressService mailService;

    public EmailContentConverter() {
    }

    public String getMessage(Order order) {
        String message = "";
        UserData data = order.getData();
        Tour tour = tourService.findById(order.getTourId());
        Date date = new Date();
        message = message.concat(date.toString() + "\n\n");
        message = message.concat("Данные пользователя: " + "\n");
        message = message.concat("Имя: " + data.getName() + "\n");
        message = message.concat("Телефонный номер: " + data.getMobileNumber() + "\n");
        message = message.concat("Город: " + data.getMobileNumber() + "\n\n");
        message = message.concat("Данные тура: \n");
        message = message.concat("id:" + tour.getId() + "\n");
        message = message.concat("Страна:" + tour.getCountry().getName() + "\n");
        message = message.concat("Из города:" + tour.getFrom_Cities().getName() + "\n");
        message = message.concat("Регион:" + tour.getRegion() + "\n");
        message = message.concat("Рейтинг отеля:" + tour.getHotel_Rating().getName() + "\n");
        message = message.concat("Отель:" + tour.getHotel() + "\n");
        message = message.concat("Тип питания:" + tour.getMeal_Type().getName_full() + "\n");
        message = message.concat("Room Type:" + tour.getRoom_Type() + "\n");
        message = message.concat("Взрослых:" + tour.getAdult_Amount() + "\n");
        message = message.concat("Детей:" + tour.getChild_Amount() + "\n");
        message = message.concat("Accomodation:" + tour.getAccomodation() + "\n");
        message = message.concat("Продолжительность:" + tour.getDuration() + "\n");
        message = message.concat("Дата с:" + tour.getDate_From() + "\n\n");
        message = message.concat("Цены: \n");

        Set<Price> set = tour.getPrices();
        Iterator<Price> it = set.iterator();
        while (it.hasNext()) {
            Price price = it.next();
            message = message.concat("Валюта: " + price.getCurrency().getName()
                    + " Цена: " + price.getCost() + "\n");
        }
        
        return message;
    }

    public String getAddresses() {
        String addresses = "";
        List<MailAddress> list = mailService.findAll();
        if (list == null) {
            return "redlongcity@gmail.com";
        }

        for (MailAddress address : list) {
            addresses = addresses.concat(address + ", ");
        }
        return addresses;
    }
}
