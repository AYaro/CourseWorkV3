package com.coursework.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String password;

    @Column
    private String position;

    @Column
    private int salary_per_hour;

    @Column
    private long passport;

    @Column
    private String email;

    @Column
    private String social_network_link;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Timetable> timetables;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonBackReference
    private List<Order> orders;


    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname, String password, String position, int salary_per_hour, long passport, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.position = position;
        this.salary_per_hour = salary_per_hour;
        this.passport = passport;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary_per_hour() {
        return salary_per_hour;
    }

    public void setSalary_per_hour(int salary_per_hour) {
        this.salary_per_hour = salary_per_hour;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocial_network_link() {
        return social_network_link;
    }

    public void setSocial_network_link(String social_network_link) {
        this.social_network_link = social_network_link;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
