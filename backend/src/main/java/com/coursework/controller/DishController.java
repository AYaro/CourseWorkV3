package com.coursework.controller;

import com.coursework.entity.Dish;
import com.coursework.entity.Order;
import com.coursework.entity.OrderedDish;
import com.coursework.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/dish")
public class DishController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
        return "asd";
    }

    @GetMapping(path="")
    public @ResponseBody
    List<Dish> getDishs() {
//        LOG.info("Reading user with id " + id + " from database.");
        List<Dish> target = new ArrayList<>();
        dishRepository.findAll().forEach(target::add);
        return target;
    }
}
