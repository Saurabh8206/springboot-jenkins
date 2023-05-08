package com.example.mailSender.controller;

import com.example.mailSender.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    @RequestMapping(value = "/sendemail")
    public String sendEmail() throws MessagingException, IOException {
        emailService.sendmail();
        return "Email sent successfully";
    }
}