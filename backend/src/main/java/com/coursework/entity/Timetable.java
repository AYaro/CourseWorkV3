package com.coursework.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Table
@Entity
public class Timetable {

    @Id
    @GeneratedValue
    public Integer id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "User_id")
    private User user;

    @Column
    private Date day;

    @Column
    private Time start_time;

    @Column
    private Time end_time;

    public Timetable(){
    }

    public Timetable(User user, Time start_time, Time end_time) {
        this.user = user;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
}
