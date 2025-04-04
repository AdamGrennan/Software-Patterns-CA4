package com.example.demo.builder;

import com.example.demo.model.User;

public interface UserBuilder {
	
    void buildUsername();
    void buildEmail();
    void buildRole();
    void buildPassword();
    void buildAddress();
    void buildPaymentMethod();
    User getResult();

}
