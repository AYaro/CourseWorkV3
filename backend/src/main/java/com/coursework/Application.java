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
            tb.setUser(user);
            userRepository.save(user);
            timetableRepository.save(tb);

            Order order = new Order();
            Dish dish = new Dish();
            OrderedDish orderedDish = new OrderedDish();

            order.setComment("adasdsfsdf");
//            order.setUser(user);
            dish.setName("Soup");
            dish.setInMenu(true);
            dish.setCategory("Categor");
            orderedDish.setDish(dish);
            orderedDish.setOrder(order);

            dishRepository.save(dish);
            orderRepository.save(order);
            orderedDishRepository.save(orderedDish);

            dish = new Dish();
            dish.setName("Жареные гвозди");
            dish.setInMenu(true);
            dish.setCategory("мемы");

            dishRepository.save(dish);

            {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo("dunaevai135@ya.ru");
                message.setSubject("Test");
                message.setText("Нео проснись ты обосрался");
                emailSender.send(message);
            }
        };
    }
}