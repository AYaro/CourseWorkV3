package com.coursework.repository;

import com.coursework.entity.OrderedDish;
import org.springframework.data.repository.CrudRepository;

public interface OrderedDishRepository extends CrudRepository<OrderedDish, Integer> {
}
