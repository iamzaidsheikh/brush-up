package io.github.brushup.registrationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brushup.registrationservice.models.Role;

public interface RoleRepo extends JpaRepository<Role, String>{
    
    Role findByRoleName(String roleName);
}
