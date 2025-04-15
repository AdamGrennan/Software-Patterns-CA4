package com.example.demo.factory;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public class AdministratorFactory extends Factory {
    @Override
    public User createUser(String username, String email, String password,
                           String address, String paymentMethod) {
    	 User user = new User();
         user.setUsername(username);
         user.setEmail(email);
         user.setPassword(password);
         user.setAddress(address);
         user.setPaymentMethod(paymentMethod);
         user.setRole(Role.ADMINISTRATOR);
         return user;
    }
}
