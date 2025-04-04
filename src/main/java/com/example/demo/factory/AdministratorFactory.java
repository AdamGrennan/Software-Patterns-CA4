package com.example.demo.factory;

import com.example.demo.model.User;
import com.example.demo.builder.UserDirector;
import com.example.demo.builder.UserBuilder;
import com.example.demo.builder.ConcreteUserBuilder;

public class AdministratorFactory extends Factory {
    @Override
    public User createUser(String username, String email, String password,
                           String address, String paymentMethod) {
        UserBuilder builder = new ConcreteUserBuilder(username, email, "Administrator", password, address, paymentMethod);
        UserDirector director = new UserDirector(builder);
        return director.constructBook();
    }
}
