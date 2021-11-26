package io.github.brushup.registrationservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brushup.registrationservice.models.User;

public interface UserRepo extends JpaRepository<User, UUID>{
    
    User findByUsername(String username);
    
    User findByEmail(String email);
}
