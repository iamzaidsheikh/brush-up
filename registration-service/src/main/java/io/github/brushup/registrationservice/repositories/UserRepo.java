package io.github.brushup.registrationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brushup.registrationservice.models.User;

public interface UserRepo extends JpaRepository<User, String>{
    
    User findByUsername(String username);
    
    User findByEmail(String email);
}
