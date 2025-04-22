package com.example.demo.proxy;

import com.example.demo.model.Role;
import com.example.demo.model.User; 

public class AdminAccess implements IAdminAccess {
    private User user;

    public AdminAccess(User user) {
        this.user = user;
    }

    @Override
    public String performAction() {
        if (user.getRole() == Role.ADMINISTRATOR) {
            return "redirect:/administrator";
        } else if (user.getRole() == Role.CUSTOMER) {
            return "redirect:/home";
        }
        return "redirect:/";
    }
}
