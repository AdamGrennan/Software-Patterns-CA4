package com.example.demo.factory;

import com.example.demo.model.User;

public abstract class Factory {
    public abstract User createUser(String username, String email, String password,
                                    String address, String paymentMethod);
}
