package com.example.demo.templateMethod;

import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.observer.LoyaltyPointsObserver;
import com.example.demo.observer.PurchaseSubject;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;

public abstract class Purchase {
	
	private Cart cart;
	
    @Autowired
    private CartRepository cartRepository; 
    
    @Autowired
    private CartService cartService;
    
	private CartItemService itemService;
	
    public Purchase(Cart cart, CartRepository cartRepository, CartItemService itemService) {
        this.cart = cart;
        this.cartRepository = cartRepository;
        this.itemService = itemService;
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
        for (CartItem item : cart.getList()) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }


    protected double applyDiscount(User user, double total) {
        return total;  
    }

    protected abstract void processPayment();
    protected abstract void saveOrder(UserOrder order);
    
    protected void addLoyaltyPoints(User user, double totalPaid) {
        PurchaseSubject subject = new PurchaseSubject();
        subject.attach(new LoyaltyPointsObserver());
        subject.setPurchase(user, totalPaid);
    }

    protected void confirm() {
        System.out.println("Order confirmed!");
    }

    protected void clearCart() {
    	cartService.clearCart(cart);
    }


}
