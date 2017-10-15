package com.smitsworks.easytour.javamail.core;

import com.smitsworks.easytour.models.MailAddress;
import com.smitsworks.easytour.models.Order;
import com.smitsworks.easytour.models.Tour;
import com.smitsworks.easytour.models.UserData;
import com.smitsworks.easytour.service.MailAddressService;
import com.smitsworks.easytour.service.TourService;
import java.util.Date;
import java.util.List;
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

    public EmailContentConverter(TourService tourService, MailAddressService mailService) {
        this.tourService = tourService;
        this.mailService = mailService;
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
