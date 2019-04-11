package com.coursework.controller;

import com.coursework.SendMailServ;
import com.coursework.entity.Order;
import com.coursework.entity.OrderedDish;
import com.coursework.repository.DishRepository;
import com.coursework.repository.OrderRepository;
import com.coursework.repository.OrderedDishRepository;
import com.coursework.repository.UserRepository;
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
@RequestMapping("/api/order")
public class    OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static final String DEFAULT_STATUS = "Ждёт подтверждения";

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final DishRepository dishRepository;

    @Autowired
    private SendMailServ sendMailServ;

    @Autowired
    private OrderedDishRepository orderedDishRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository, DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
    }

    @RequestMapping(path = "/hello")
    public @ResponseBody
    String sayHello() {
        LOG.info("GET called on /hello resource");
//        sendMailServ.SendMail("dunaevai135@ya.ru", "Test", "test");
//        orderRepository.findById(1).get().getOrderedDishes().get(0);
        return "asd";
    }

    @RequestMapping(path = "/hello2")
    public @ResponseBody
    List<OrderedDish> sayHello2() {
        LOG.info("GET called on /hello resource");
//        sendMailServ.SendMail("dunaevai135@ya.ru", "Test", "test");
//        orderRepository.findById(1).get().getOrderedDishes().get(0);
        return orderRepository.findById(1).get().getOrderedDishes();
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public @ResponseBody long addNewOrder (@RequestParam Integer table_number,
                                           @RequestParam(required = false) String comment,
                                           @RequestBody List<OrderedDish> orderedDishes) {
        Order order = new Order(table_number, comment, DEFAULT_STATUS, new Timestamp(System.currentTimeMillis()));
//        LOG.info("ordered: " + orderedDishes.get(0).getQuantity());
        orderedDishes.forEach(o -> {
            o.setOrder(order);
            o.setDish(dishRepository.findById(o.getDish().getId()).get());
        });
        order.setOrderedDishes(orderedDishes);

        orderRepository.save(order);

        orderedDishes.forEach(o -> {
            orderedDishRepository.save(o);
        });

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
        orderRepository.findAll().forEach(o -> {if (o.getEnd_time() == null) {target.add(o);}});
        return target;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List<Order> getAllOrders() {
//        LOG.info("Reading user with id " + id + " from database.");
        List<Order> target = new ArrayList<>();
        orderRepository.findAll().forEach(target::add);
        return target;
    }
}