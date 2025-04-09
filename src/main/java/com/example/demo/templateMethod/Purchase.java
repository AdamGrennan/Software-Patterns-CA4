package com.example.demo.templateMethod;

import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.model.Cart;
import com.example.demo.model.OrderItem;

public abstract class Purchase {
	
	private Cart cart;
	
	public Purchase(Cart cart) {
		this.cart = cart;
	}

    public final void completeOrder(UserOrder order) {
        validateCart();
        double total = calculateTotal();
        double discountedTotal  = applyDiscount(order.getUser(), total);  
        order.setTotal(discountedTotal);
        processPayment();
        saveOrder(order);
        addLoyaltyPoints(order.getUser(), total);
        confirm();
        clearCart();
    }

    protected void validateCart() {
        System.out.println("Validating cart...");
    }

    protected double calculateTotal() {
        double total = 0;
        for(OrderItem item: cart.getList()) {
        	total += item.getPrice();
        }
        return total;
    }

    protected double applyDiscount(User user, double total) {
        return total;  
    }

    protected abstract void processPayment();
    protected abstract void saveOrder(UserOrder order);
    
    protected void addLoyaltyPoints(User user, double totalPaid) {
        int points = (int) totalPaid;
        int current = user.getLoyaltyPoints();
        user.setLoyaltyPoints(current + points);
        System.out.println("Added " + points + " loyalty points to your card.");
    }

    protected void confirm() {
        System.out.println("Order confirmed!");
    }

    protected void clearCart() {
        cart.getList().clear();
    }
}
