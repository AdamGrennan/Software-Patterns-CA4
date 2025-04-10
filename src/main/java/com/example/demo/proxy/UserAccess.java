package com.example.demo.proxy;

import com.example.demo.model.User;

public class UserAccess implements IUserAccess{
	  private User user;

	    public UserAccess(User user) {
	        this.user = user;
	    }


	    @Override
	    public String accessDashboard() {
	        if (user.getRole().equalsIgnoreCase("Administrator")) {
	            return "redirect:/administrator";
	        } else if (user.getRole().equalsIgnoreCase("Customer")) {
	            return "redirect:/home";
	        }
	        return "redirect:/guest";
	    }


}
