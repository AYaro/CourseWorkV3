package com.coursework.controller;

import com.coursework.entity.User;

public class Authorization {
    private boolean isAuthorizationActive = false;
    private User user;

    public void authorizeAs(User user) {
        this.user = user;
        this.isAuthorizationActive = true;
    }

    public User getLastUser() {
        return user;
    }

    public void rejectAuthorization() {
        this.isAuthorizationActive = false;
    }

    public boolean isAuthorized() {
        return isAuthorizationActive;
    }
}
