package com.coursework;

import com.coursework.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailServ {
    public final JavaMailSender emailSender;

    public final String NEW_USER_HELLO_SUBJECT = "Привет";
    public final String NEW_USER_HELLO_TEXT = "Новый сотрудник";

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public SendMailServ(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void SendMail (String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Парользабыл <javamail@dunaevai135.me>");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        LOG.info("message: " + message.toString());
//        emailSender.send(message);
    }
}
