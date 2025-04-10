package com.example.demo.factory;

import com.example.demo.builder.UserDirector;
import com.example.demo.builder.UserBuilder;
import com.example.demo.builder.ConcreteUserBuilder;
import com.example.demo.model.User;

public class CustomerFactory extends Factory {
    @Override
    public User createUser(String username, String email, String password,
                           String address, String paymentMethod) {
        UserBuilder builder = new ConcreteUserBuilder(username, email, "Customer", password, address, paymentMethod);
        UserDirector director = new UserDirector(builder);
        return director.constructUser(); 
    }
}
