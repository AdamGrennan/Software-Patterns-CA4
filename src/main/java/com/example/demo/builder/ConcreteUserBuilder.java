package com.example.demo.builder;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public class ConcreteUserBuilder implements UserBuilder {
    private User user;

    public ConcreteUserBuilder(String username, String email, Role role,
                                String password, String address, String paymentMethod) {
        this.user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password);
        user.setAddress(address);
        user.setPaymentMethod(paymentMethod);
    }

    @Override 
    public void buildUsername() {}
    
    @Override 
    public void buildEmail() {}
    
    @Override 
    public void buildRole() {}
    
    @Override 
    public void buildPassword() {}
    
    @Override 
    public void buildAddress() {}
    
    @Override 
    public void buildPaymentMethod() {}

    @Override
    public User getResult() {
        return user;
    }
}
