package com.coursework;

import com.coursework.entity.Timetable;
import com.coursework.entity.User;
import com.coursework.repository.TimetableRepository;
import com.coursework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, TimetableRepository timetableRepository) {
        return (args) -> {
//            // save a couple of customers
            userRepository.save(new User("Jack", "Bauer"));
            userRepository.save(new User("Chloe", "O'Brian"));
            userRepository.save(new User("Kim", "Bauer"));
            userRepository.save(new User("Kim", "Bauer"));
            User user = new User("lol", "lolov");
            Timetable tb = new Timetable();
            tb.setUser(user);
            userRepository.save(user);
            timetableRepository.save(tb);
////            timetableRepository.save(new Timetable(new Time(17, 1, 0), new Time(19, 4, 1)));
////            timetableRepository.save(new Timetable(new Time(17, 1, 0), new Time(19, 4, 1), 1));
//
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (User customer : userRepository.findAll()) {
//                log.info(customer.getId().toString());
////                log.info(customer.getTimetables().toString());
//            }
////            Integer i = 4;
////            log.info(timetableRepository.findByIdUser(userRepository.findByName("Kima").getId()).toString());
////            log.info(userRepository.findByName("Jack").getId().toString());
//            log.info("");

        };
    }
}