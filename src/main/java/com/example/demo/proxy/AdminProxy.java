package com.example.demo.proxy;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public class AdminProxy implements IAdminAccess{

	 private User user;
	 private AdminAccess realAccess;

	 public AdminProxy(User user) {
	        this.user = user;
	    }

	 @Override
	 public String performAction() {
	     if (user.getRole() == Role.ADMINISTRATOR) {

	         realAccess = new AdminAccess(user);
	         return realAccess.performAction();

	     } else {
	         return "unauthorized";
	     }
	 }
}
