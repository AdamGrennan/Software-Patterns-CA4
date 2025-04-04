package com.example.demo.factory;

import com.example.demo.model.User;

import jakarta.persistence.Entity;

@Entity
public class UserCustomer extends User {
    public UserCustomer() {
        this.setRole("Customer");
    }
}