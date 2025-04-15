package com.example.demo.observer;
import com.example.demo.model.User;
import java.util.ArrayList;
import java.util.List;

public class PurchaseSubject {
    private List<PointObserver> observers = new ArrayList<>();
    private User user;
    private double totalSpent;

    public void attach(PointObserver o) {
        observers.add(o);
    }
    
    public void detach(PointObserver o){
    	observers.remove(o);
    }

    public void setPurchase(User user, double totalSpent) {
        this.user = user;
        this.totalSpent = totalSpent;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (PointObserver o : observers) {
            o.update(user, totalSpent);
        }
    }
}
