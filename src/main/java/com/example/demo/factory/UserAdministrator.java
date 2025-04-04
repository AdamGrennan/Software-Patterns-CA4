package com.example.demo.factory;

import com.example.demo.model.User;

import jakarta.persistence.Entity;

@Entity
public class UserAdministrator extends User {
    public UserAdministrator() {
        this.setRole("Administrator");
    }
}