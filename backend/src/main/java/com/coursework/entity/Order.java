package com.coursework.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    private Integer table_number;

    @Column
    private String Comment;

    @Column
    private String Status;

    @Column
    private Timestamp start_time;

    @Column
    private Timestamp end_time;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "User_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderedDish> orderedDishes;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTable() {
        return table_number;
    }

    public void setTable(Integer table) {
        this.table_number = table;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
