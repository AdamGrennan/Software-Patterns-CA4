package com.example.demo.service;

import com.example.demo.factory.AdministratorFactory;
import com.example.demo.factory.CustomerFactory;
import com.example.demo.factory.Factory;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public List<User> getAllCustomers() {
	    return userRepository.findByRole(Role.CUSTOMER);
	}


	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User addUser(User user) {
		User existingUser = userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		if(existingUser != null) {
			return null;
		}else {
			 Factory factory;
			    if (user.getRole() == Role.ADMINISTRATOR) {
			        factory = new AdministratorFactory();
			    } else {
			        factory = new CustomerFactory(); 
			    }

			    User newUser = factory.createUser(user.getUsername(), user.getEmail(),
			    		user.getPassword(), user.getAddress(), user.getPaymentMethod());
		    return userRepository.save(newUser);
		}
	}
	
	@Override
	public User login(String email, String password) {
	    return userRepository.findByEmailAndPassword(email, password);
	}


	@Override
	public User updateUser(Long id, User updatedUser) {
		Optional<User> existingUserOptional = userRepository.findById(id);
		if (existingUserOptional.isPresent()) {
			User existingUser = existingUserOptional.get();
			existingUser.setUsername(updatedUser.getUsername());
			existingUser.setPassword(updatedUser.getPassword());
			return userRepository.save(existingUser);
		} else {
			return null;
		}
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
}