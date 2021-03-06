package com.coursework.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class Dish {
    @Id
    @GeneratedValue
    public Integer id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private int price;

    @Column
    private int weight;

    @Column
    private String description;

    @Column(columnDefinition="TEXT")
    private String image;

    @Column
    private boolean inMenu;

    @ManyToMany(mappedBy = "dishes")
    Set<Recipe> dishRecipes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private List<OrderedDish> orderedDishes;

    public Dish() {
    }

    public Dish(String name, String category, int price, int weight, String description, String image, boolean inMenu) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.weight = weight;
        this.description = description;
        this.image = image;
        this.inMenu = inMenu;
    }

    public Set<Recipe> getDishRecipes() {
        return dishRecipes;
    }

    public void setDishRecipes(Set<Recipe> dishRecipes) {
        this.dishRecipes = dishRecipes;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isInMenu() {
        return inMenu;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }
}
