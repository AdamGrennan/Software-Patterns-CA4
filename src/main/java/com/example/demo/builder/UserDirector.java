package com.example.demo.builder;

import com.example.demo.model.User;

public class UserDirector {
    private UserBuilder builder;

    public UserDirector(UserBuilder builder) {
        this.builder = builder;
    }

    public User constructUser() {
        builder.buildUsername();
        builder.buildEmail();
        builder.buildRole();
        builder.buildPassword();
        builder.buildAddress();
        builder.buildPaymentMethod();
        return builder.getResult();
    }
}
