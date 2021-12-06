package io.github.brushup.registrationservice.services;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.brushup.registrationservice.exceptions.EmailAlreadyExistsException;
import io.github.brushup.registrationservice.exceptions.UsernameAlreadyExistsException;
import io.github.brushup.registrationservice.models.Role;
import io.github.brushup.registrationservice.models.User;
import io.github.brushup.registrationservice.models.VerificationToken;
import io.github.brushup.registrationservice.repositories.RoleRepo;
import io.github.brushup.registrationservice.repositories.UserRepo;
import io.github.brushup.registrationservice.repositories.VerificationTokenRepo;
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
    private final VerificationTokenRepo tokenRepo;

    private boolean emailExists(String email) {
        return userRepo.findByEmail(email) != null;
    }

    private boolean usernameExists(String username) {
        return userRepo.findByUsername(username) != null;
    }

    @Override
    public String enableUser(User user) {
        log.info("{} verified. Account enabled", user.getEmail());
        user.setEnabled(true);

        return userRepo.save(user).getId();
    } 
    
    @Override
    public String registerUser(User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException{
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

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepo.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        
        return tokenRepo.findByToken(VerificationToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken newToken = tokenRepo.findByToken(existingVerificationToken);
        newToken.updateToken(UUID.randomUUID()
            .toString());
            newToken = tokenRepo.save(newToken);

        return newToken;
    }

    @Override
    public User getUser(final String verificationToken) {
        final VerificationToken token = tokenRepo.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }
}
