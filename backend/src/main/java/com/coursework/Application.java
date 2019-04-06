package com.coursework;

import com.coursework.entity.*;
import com.coursework.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, TimetableRepository timetableRepository, OrderRepository orderRepository, OrderedDishRepository orderedDishRepository, DishRepository dishRepository) {
        return (args) -> {
            userRepository.save(new User("Jack", "Bauer"));
            userRepository.save(new User("Chloe", "O'Brian"));
            userRepository.save(new User("Kim", "Bauer"));
            userRepository.save(new User("Kim", "Bauer"));
            User user = new User("lol", "lolov");
            Timetable tb = new Timetable();
            tb.setUser(user);
            userRepository.save(user);
            timetableRepository.save(tb);

            Order order = new Order();
            Dish dish = new Dish();
            OrderedDish orderedDish = new OrderedDish();

            order.setComment("adasdsfsdf");
            order.setUser(user);
            dish.setName("Soup");
            orderedDish.setDish(dish);
            orderedDish.setOrder(order);

            dishRepository.save(dish);
            orderRepository.save(order);
            orderedDishRepository.save(orderedDish);

        };
    }
}