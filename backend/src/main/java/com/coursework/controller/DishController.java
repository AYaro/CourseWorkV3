package com.coursework.controller;

import com.coursework.entity.Dish;
import com.coursework.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public long addNewDish(@RequestParam String name,
                           @RequestParam String category,
                           @RequestParam int price,
                           @RequestParam int weight,
                           @RequestParam String description,
                           @RequestParam boolean inMenu,
                           @RequestParam MultipartFile image) throws IOException {
        Dish dish = new Dish(name, category, price, weight, description, Base64.getEncoder().encodeToString(image.getBytes()), inMenu);
        dishRepository.save(dish);
        return dish.getId();
    }


    @GetMapping(path="/categories")
    public @ResponseBody
    Set<String> getAllDishCategory() {
//        LOG.info("Reading user with id " + id + " from database.");
        Set<String> target = new TreeSet<>();
        dishRepository.findAll().forEach(o -> {if (o.isInMenu() && o.getCategory() != null) {target.add(o.getCategory());}});
        return target;
    }

    @GetMapping(path="")
    public @ResponseBody
    List<Dish> getDishes() {
//        LOG.info("Reading user with id " + id + " from database.");
        List<Dish> target = new ArrayList<>();
        dishRepository.findAll().forEach(o -> {if (o.isInMenu()) {target.add(o);}});
        return target;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    List<Dish> getAllDishes() {
//        LOG.info("Reading user with id " + id + " from database.");
        List<Dish> target = new ArrayList<>();
        dishRepository.findAll().forEach(target::add);
        return target;
    }
}
