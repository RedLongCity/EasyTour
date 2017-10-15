package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.javamail.core.Email;
import com.smitsworks.easytour.javamail.core.EmailConfiguration;
import com.smitsworks.easytour.javamail.core.EmailSender;
import com.smitsworks.easytour.javamail.core.SimpleEmail;
import com.smitsworks.easytour.javamail.core.SimpleEmailSender;
import javax.mail.MessagingException;
import org.hibernate.criterion.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author redlongcity controller for manipulations with mail resources
 */
@RestController
@RequestMapping("/json/mail")
public class MailController {

    @RequestMapping(value = "/postorder", method = RequestMethod.POST)
    public ResponseEntity<Void> send(@RequestBody Order order) {

        String from = "redlongcity@gmail.com";
        String to = "redlongcity@gmail.com";
        String subject = "some subject";
        String message = "some message";

        Email email = new SimpleEmail(from, to, subject, message);
        EmailSender sender = new SimpleEmailSender(new EmailConfiguration());

        try {
            sender.send(email);
            System.out.println("Sent message succesfully!");
        } catch (MessagingException e) {
            System.err.println("Message not sent!");
            e.printStackTrace();
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
