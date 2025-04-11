package com.example.demo.observer;

import com.example.demo.model.User;

public interface PointObserver {
    void update(User user, double total);
}
