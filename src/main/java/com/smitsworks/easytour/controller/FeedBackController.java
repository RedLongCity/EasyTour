package com.smitsworks.easytour.controller;

import com.smitsworks.easytour.javamail.core.Email;
import com.smitsworks.easytour.javamail.core.EmailConfiguration;
import com.smitsworks.easytour.javamail.core.EmailContentConverter;
import com.smitsworks.easytour.javamail.core.EmailSender;
import com.smitsworks.easytour.javamail.core.SimpleEmail;
import com.smitsworks.easytour.javamail.core.SimpleEmailSender;
import com.smitsworks.easytour.models.FeedBack;
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
 *
 * @author redlongcity controller for feedback handling
 */
@RestController
@RequestMapping("/json")
public class FeedBackController {

    private static final Logger LOG = Logger.getLogger(FeedBackController.class.getName());

    @Autowired
    private EmailContentConverter converter;

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public ResponseEntity<Void> send(@RequestBody FeedBack feedBack) {

        String from = "HotToursUkraine@gmail.com";
        String to = "redlongcity@gmail.com";
        String subject = "FeedBack";
        String message = converter.getFeedBack(feedBack);

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
