package com.example.demo.proxy;

import com.example.demo.model.Role;
import com.example.demo.model.User; 

public class UserAccess implements IUserAccess {
    private User user;

    public UserAccess(User user) {
        this.user = user;
    }

    @Override
    public String accessDashboard() {
        if (user.getRole() == Role.ADMINISTRATOR) {
            return "redirect:/administrator";
        } else if (user.getRole() == Role.CUSTOMER) {
            return "redirect:/home";
        }
        return "redirect:/";
    }
}
