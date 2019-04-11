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
    public JavaMailSender emailSender;

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
            User user = new User("urname", (new BCryptPasswordEncoder().encode("password")), "mail@mail.com");
            user.setAuthority(AuthorityType.ROLE_ADMIN);
            Timetable tb = new Timetable();
            userRepository.save(user);
            tb.setUser(user);
            tb.setDay(new Date());
            tb.setStart_time(new Time(1, 2, 4));
            tb.setEnd_time(new Time(3, 2, 4));
            timetableRepository.save(tb);
            tb = new Timetable();
            tb.setUser(user);
            tb.setDay(new Date());
            tb.setStart_time(new Time(6, 2, 4));
            tb.setEnd_time(new Time(9, 2, 4));
            timetableRepository.save(tb);

            Order order = new Order();
            Dish dish = new Dish();
            Dish dish2 = new Dish();
            Dish dish3 = new Dish();
            OrderedDish orderedDish = new OrderedDish();
            OrderedDish orderedDish2 = new OrderedDish();
            OrderedDish orderedDish3 = new OrderedDish();

            order.setComment("adasdsfsdf");
            order.setTable(2);
            order.setStatus("not ready");
            dish.setName("Soup");
            dish.setInMenu(true);
            dish.setCategory("Categor");
            orderedDish.setDish(dish);
            orderedDish.setQuantity(3);
            orderedDish.setOrder(order);
            orderedDish2.setDish(dish2);
            orderedDish2.setQuantity(5);
            orderedDish2.setOrder(order);
            orderedDish3.setDish(dish3);
            orderedDish3.setQuantity(1);
            orderedDish3.setOrder(order);


            dishRepository.save(dish);
            orderRepository.save(order);
            dishRepository.save(dish2);;
            dishRepository.save(dish3);

            orderedDishRepository.save(orderedDish);
            orderedDishRepository.save(orderedDish2);
            orderedDishRepository.save(orderedDish3);

            Dish dish5 = new Dish();
            dish5.setName("Жареные гвозди");
            dish5.setInMenu(true);
            dish5.setCategory("мемы");

            dishRepository.save(dish5);


        };
    }
}