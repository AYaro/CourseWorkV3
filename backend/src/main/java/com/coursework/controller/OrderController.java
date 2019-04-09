package com.coursework.controller;

import com.coursework.entity.Order;
import com.coursework.entity.User;
import com.coursework.repository.OrderRepository;
import com.coursework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/order")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
        return "asd";
    }

//    @RequestMapping(path = "/", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody long addNewOrder (@RequestParam String firstName, @RequestParam String lastName) {
//        User user = new User(firstName, lastName);
//        userRepository.save(user);
//
//        LOG.info(user.toString() + " successfully saved into DB");
//
//        return user.getId();
//    }

    @GetMapping(path="/{id}")
    public @ResponseBody Order getOrderById(@PathVariable("id") Integer id) {
//        LOG.info("Reading user with id " + id + " from database.");
        return orderRepository.findById(id).get();
    }

    @GetMapping(path="/")
    public @ResponseBody
    List<Order> getOrders() {
//        LOG.info("Reading user with id " + id + " from database.");
        List<Order> target = new ArrayList<>();
        orderRepository.findAll().forEach(target::add);
        return target;
    }
}