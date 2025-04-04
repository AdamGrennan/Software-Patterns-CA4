package com.example.demo.builder;

import com.example.demo.factory.UserAdministrator;
import com.example.demo.factory.UserCustomer;
import com.example.demo.model.User;

public class ConcreteUserBuilder implements UserBuilder {
    private User user;

    private String username;
    private String email;
    private String role;
    private String password;
    private String address;
    private String paymentMethod;

    public ConcreteUserBuilder(String username, String email, String role,
                                String password, String address, String paymentMethod) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
        this.address = address;
        this.paymentMethod = paymentMethod;
        
        if (role.equalsIgnoreCase("Administrator")) {
            this.user = new UserAdministrator();
        } else if (role.equalsIgnoreCase("Customer")) {
            this.user = new UserCustomer();
        } else {
            this.user = new User();
            this.user.setRole(role);
        }
    }

    @Override
    public void buildUsername() {
        user.setUsername(username);
    }

    @Override
    public void buildEmail() {
        user.setEmail(email);
    }

    @Override
    public void buildRole() {
        user.setRole(role);
    }

    @Override
    public void buildPassword() {
        user.setPassword(password);
    }

    @Override
    public void buildAddress() {
        user.setAddress(address);
    }

    @Override
    public void buildPaymentMethod() {
        user.setPaymentMethod(paymentMethod);
    }

    @Override
    public User getResult() {
        return user;
    }
}
