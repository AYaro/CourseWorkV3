package com.coursework;

import com.coursework.config.AuthorityType;
import com.coursework.entity.*;
import com.coursework.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Time;
import java.util.Date;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private SendMailServ sendMailServ;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, TimetableRepository timetableRepository, OrderRepository orderRepository, OrderedDishRepository orderedDishRepository, DishRepository dishRepository) {
        return (args) -> {



//            sendMailServ.SendMail("dunaevai135", "Привет", "Новый сотрудник");

        };
    }
}