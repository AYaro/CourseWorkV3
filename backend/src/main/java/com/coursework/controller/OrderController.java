package com.coursework.controller;

import com.coursework.entity.Order;
import com.coursework.entity.OrderedDish;
import com.coursework.entity.User;
import com.coursework.repository.OrderRepository;
import com.coursework.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/order")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static final String DEFAULT_STATUS = "Not assigned";

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

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public @ResponseBody long addNewOrder (@RequestParam Integer table_number,
                                           @RequestParam String comment,
                                           @RequestParam List<OrderedDish> orderedDishes) {
        Order order = new Order(table_number, comment, DEFAULT_STATUS, new Timestamp(System.currentTimeMillis()), orderedDishes);
        orderRepository.save(order);

        LOG.info(order.toString() + " successfully saved into DB");

        return order.getId();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public @ResponseBody long changeOrder (@PathVariable("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        orderRepository.findById(id).get().setUser();

        LOG.info(order.toString() + " successfully saved into DB");

        return order.getId();
    }

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