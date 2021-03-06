package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.javamail.core.Email;
import com.smitsworks.easytour.javamail.core.EmailConfiguration;
import com.smitsworks.easytour.javamail.core.EmailContentConverter;
import com.smitsworks.easytour.javamail.core.EmailSender;
import com.smitsworks.easytour.javamail.core.SimpleEmail;
import com.smitsworks.easytour.javamail.core.SimpleEmailSender;
import com.smitsworks.easytour.models.Order;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author redlongcity 
 * controller for manipulations with mail resources
 */
@RestController
@RequestMapping("/json")
public class MailController {

    private static final Logger LOG = Logger.getLogger(MailController.class.getName());

    @Autowired
    EmailContentConverter converter;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Void> send(@RequestBody Order order) {

        String from = "HotToursUkraine@gmail.com";
        String to = converter.getAddresses();
        String subject = "Order";
        String message = converter.getMessage(order);

        Email email = new SimpleEmail(from, to, subject, message);
        EmailSender sender = new SimpleEmailSender(new EmailConfiguration());

        try {
            sender.send(email);
            LOG.log(Level.INFO, "Sent message succesfully!");
        } catch (MessagingException e) {
            LOG.log(Level.INFO, "Sending message was failed! " + e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
