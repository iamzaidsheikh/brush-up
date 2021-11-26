package io.github.brushup.registrationservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brushup.registrationservice.models.Role;

public interface RoleRepo extends JpaRepository<Role, UUID>{
    
    Role findByRoleName(String roleName);
}
