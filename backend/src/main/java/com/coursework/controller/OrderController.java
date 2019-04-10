package com.coursework.controller;

import com.coursework.entity.Order;
import com.coursework.entity.OrderedDish;
import com.coursework.entity.PdfUserDetails;
import com.coursework.entity.User;
import com.coursework.repository.OrderRepository;
import com.coursework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@RestController()
@RequestMapping("/api/order")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static final String DEFAULT_STATUS = "Ждёт подтверждения";

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
        return "asd";
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public @ResponseBody long addNewOrder (@RequestParam Integer table_number,
                                           @RequestParam String comment,
                                           @RequestParam List<OrderedDish> orderedDishes) {
        Order order = new Order(table_number, comment, DEFAULT_STATUS, new Timestamp(System.currentTimeMillis()), orderedDishes);
        orderRepository.save(order);

        LOG.info(order.toString() + " successfully saved into DB");

        return order.getId();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public @ResponseBody long changeOrder (@PathVariable("id") Integer id, @RequestParam String status) {
        Order order = orderRepository.findById(id).get();
        if (order.getUser() == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            order.setUser(userRepository.getUserByUsername(auth.getName()));
//            order.setUser((User) auth.getDetails());
        }
        order.setStatus(status);
        LOG.info(order.toString() + " successfully changed");
        orderRepository.save(order);
        return order.getId();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public @ResponseBody long endOrder (@PathVariable("id") Integer id, @RequestParam Timestamp endTime ) {
        Order order = orderRepository.findById(id).get();
        order.setEnd_time(endTime);
//        LOG.info(order.toString() + " successfully changed");
        orderRepository.save(order);
        return order.getId();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Order getOrderById(@PathVariable("id") Integer id) {
//        LOG.info("Reading user with id " + id + " from database.");
        return orderRepository.findById(id).get();
    }

    @GetMapping(path="")
    public @ResponseBody
    List<Order> getOrders() {
//        LOG.info("Reading user with id " + id + " from database.");
        List<Order> target = new ArrayList<>();
        orderRepository.findAll().forEach(target::add);
        return target;
    }
}