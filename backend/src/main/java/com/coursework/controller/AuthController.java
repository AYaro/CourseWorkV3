package com.coursework.controller;

import  com.coursework.entity.User;
import  com.coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @SessionAttribute Authorization authorization
    ) {
        if (username.length() < 3 || username.length() > 20 || password.length() < 5) {
            return new ApiResponse("Bad user data", 400).getResponseEntity();
        }

        if (userRepository.existsByUsername(username)) {
            return new ApiResponse("UR001 - User with such username already exists", 400).getResponseEntity();
        } else if (userRepository.existsByEmail(email)) {
            return new ApiResponse("UR002 - User with such email already exists", 400).getResponseEntity();
        } else {
            try {
                User newUser = new User(username, passwordEncoder.encode(password), email);
                userRepository.save(newUser);
                authorization.authorizeAs(newUser);
            } catch (TransactionSystemException ex) {
                if (ex.getCause() != null && ex.getCause().getCause() != null &&
                        ex.getCause().getCause().getClass() == ConstraintViolationException.class) {
                    return new ApiResponse("Bad user data", 400).getResponseEntity();
                } else {
                    return new ApiResponse("Couldn't save user data", 500).getResponseEntity();
                }
            }
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestParam String username,
            @RequestParam String password,
            @SessionAttribute Authorization authorization
    ) {
        if (authorization.isAuthorized()) {
            return new ApiResponse("UT001 - Already authorized", 400).getResponseEntity();
        }

        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            authorization.authorizeAs(user);
            return ResponseEntity.ok(null);
        } else {
            return new ApiResponse("UT002 - Wrong password or username", 400).getResponseEntity();
        }
    }

    @GetMapping("/identity")
    public Map<String, String> identity(@SessionAttribute Authorization authorization) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("username", authorization.getLastUser().getUsername());
        data.put("email", authorization.getLastUser().getEmail());
        return data;
    }

    @PostMapping("/logout")
    public ResponseEntity logout(
            @SessionAttribute Authorization authorization
    ) {
        authorization.rejectAuthorization();
        return ResponseEntity.ok(null);
    }
}
