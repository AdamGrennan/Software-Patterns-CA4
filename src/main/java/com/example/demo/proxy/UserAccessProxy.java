package com.example.demo.proxy;

import com.example.demo.model.User;

public class UserAccessProxy implements IUserAccess{

	 private User user;
	 private UserAccess realAccess;

	 public UserAccessProxy(User user) {
	        this.user = user;
	    }

	 @Override
	 public String accessDashboard() {
	     if (user == null || user.getRole() == null) {
	         return "redirect:/login"; 
	     }

	     realAccess = new UserAccess(user);
	     return realAccess.accessDashboard();
	 }


}
