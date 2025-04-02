package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
    User findByEmailAndPassword(String email, String password);
    User findByUsernameAndEmail(String username, String email);
    boolean existsByEmail(String email);       
}
