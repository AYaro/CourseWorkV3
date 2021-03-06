package com.coursework.repository;

import com.coursework.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User getUserByUsername(String username);

}
