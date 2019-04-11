package com.coursework.controller;

import com.coursework.SendMailServ;
import com.coursework.entity.Order;
import com.coursework.entity.Timetable;
import com.coursework.entity.User;
import com.coursework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SendMailServ sendMailServ;

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody long addNewUser (@RequestParam String name,
                                          @RequestParam String surname,
                                          @RequestParam String password,
                                          @RequestParam String position,
                                          @RequestParam int salary_per_hour,
                                          @RequestParam long passport,
                                          @RequestParam String email) {
        User user = new User(name, surname, new BCryptPasswordEncoder().encode(password), position, salary_per_hour, passport, email);
        userRepository.save(user);

        sendMailServ.SendMail(email, sendMailServ.NEW_USER_HELLO_SUBJECT, sendMailServ.NEW_USER_HELLO_TEXT);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    @RequestMapping(path = "", method = RequestMethod.PATCH)
    public @ResponseBody long changeUser (@RequestParam User user) {
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }


    @GetMapping(path="/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") Integer id) {
        LOG.info("Reading user with id " + id + " from database.");
        return userRepository.findById(id).get();
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List<User> getUsers() {
        List<User> target = new ArrayList<>();
        userRepository.findAll().forEach(target::add);
        return target;
    }

    @GetMapping(path = "/table")
    public @ResponseBody
    List<Timetable> getUserTable () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getUserByUsername(auth.getName()).getTimetables();
    }

}