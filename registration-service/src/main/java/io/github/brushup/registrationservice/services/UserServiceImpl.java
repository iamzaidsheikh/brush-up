package io.github.brushup.registrationservice.services;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.brushup.registrationservice.exceptions.EmailAlreadyExistsException;
import io.github.brushup.registrationservice.exceptions.UsernameAlreadyExistsException;
import io.github.brushup.registrationservice.models.Role;
import io.github.brushup.registrationservice.models.User;
import io.github.brushup.registrationservice.repositories.RoleRepo;
import io.github.brushup.registrationservice.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    private boolean emailExists(String email) {
        return userRepo.findByEmail(email) != null;
    }

    private boolean usernameExists(String username) {
        return userRepo.findByUsername(username) != null;
    }
    
    @Override
    public Long registerUser(User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException{
        if(emailExists(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        } 
        else if(usernameExists(user.getUsername())) {
            throw new UsernameAlreadyExistsException(user.getUsername());
        }
        log.info("Registering new user {}",user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepo.findByRoleName("ROLE_USER");
        user.getRoles().add(userRole);
        User registeredUser = userRepo.save(user);

        return registeredUser.getId();
    }
        
}
