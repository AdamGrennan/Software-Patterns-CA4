package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.discountStrategy.LoyaltyDiscount;
import com.example.demo.discountStrategy.PromoDiscount;
import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemService itemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Override
	public Cart addToCart(Long bookId, Long userId) {
	    Book book = bookService.getBookById(bookId);
	    User user = userService.getUserById(userId);
	    
	    if(book.getStock() == 0) {
	    	throw new IllegalStateException("This item is currently out of stock.");
	    }

	    Cart cart = cartRepository.findByUser(user);
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	        cart = cartRepository.save(cart);
	    }
	    for (CartItem existingItem : cart.getList()) {
	        if (existingItem.getBook().getId().equals(bookId)) {
	            existingItem.setQuantity(existingItem.getQuantity() + 1);
	            itemService.addCartItem(existingItem);
	            return cartRepository.findByUser(user); 
	        }
	    }

	    CartItem item = new CartItem();
	    item.setBook(book);
	    item.setQuantity(1);
	    item.setPrice(book.getPrice());
	    item.setCart(cart);

	    itemService.addCartItem(item);
	    return cartRepository.findByUser(user);
	}

	@Override
	public Cart getCart(User user) {
	    Cart cart = cartRepository.findByUser(user);
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	        cart.setTotalPrice(0);
	        return cart;
	    }

	    cart.getList().clear();  
	    cart.getList().addAll(itemService.getByCart(cart));

	    double total = 0;
	    for (CartItem item : cart.getList()) {
	        total += item.getPrice() * item.getQuantity();
	    }

	    cart.setTotalPrice(total);  
	    
	    return cart;
	}

	@Override
	public void removeFromCart(User user, Long bookId) {
	    Cart cart = cartRepository.findByUser(user);

	    CartItem itemToRemove = null;

	    for (CartItem item : cart.getList()) {
	        if (item.getBook().getId().equals(bookId)) {
	            itemToRemove = item;
	            break;
	        }
	    }
	    if (itemToRemove != null) {
	        cart.getList().remove(itemToRemove);       
	        itemService.deleteCartItem(itemToRemove.getId());   
	        cartRepository.save(cart);                  
	    }
	}
	
	@Override
	public void clearCart(Cart cart) {
	    for (CartItem item : cart.getList()) {
	        itemService.deleteCartItem(item.getId()); 
	    }
	    cart.getList().clear();                      
	    cart.setTotalPrice(0);                       
	    cartRepository.save(cart);                   
	}

	
	public double getDiscountedTotal(User user, String promoCode, boolean usePoints) {
	    Cart cart = getCart(user);
	    double total = cart.getTotalPrice();

	    if (usePoints) {
	        total = new LoyaltyDiscount().applyDiscount(user, total);
	    }

	    if (promoCode != null && !promoCode.isEmpty()) {
	        total = new PromoDiscount(promoCode).applyDiscount(user, total);
	    }

	    return total;
	}


}
