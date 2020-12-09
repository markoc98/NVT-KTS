package com.nwt_kts_project.CulturalOfferings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    private JavaMailSender emailSender;

    @Autowired
    public EmailSenderService(JavaMailSender emailSender)
    {
        this.emailSender = emailSender;
    }

    @Async
    public void sendMail(SimpleMailMessage email)
    {
        emailSender.send(email);
    }
}
