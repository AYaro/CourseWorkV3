package com.coursework.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Table
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Time type;

    @ManyToMany
    @JoinTable(name = "dish_recipe", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "dish_id"))
    Set<Dish> dishes;

    public Recipe() {
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getType() {
        return type;
    }

    public void setType(Time type) {
        this.type = type;
    }
}
