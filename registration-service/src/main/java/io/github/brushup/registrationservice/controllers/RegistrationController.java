package io.github.brushup.registrationservice.controllers;

import java.net.URI;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.brushup.registrationservice.exceptions.VerificationTokenDoesNotExistException;
import io.github.brushup.registrationservice.exceptions.VerificationTokenExpiredException;
import io.github.brushup.registrationservice.listeners.OnRegistrationCompleteEvent;
import io.github.brushup.registrationservice.models.User;
import io.github.brushup.registrationservice.models.VerificationToken;
import io.github.brushup.registrationservice.services.IUserService;
import io.github.brushup.registrationservice.utils.RegistrationResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final IUserService userService;
    private final ApplicationEventPublisher eventPublisher;
    
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid User user, HttpServletRequest request) {

        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath("/register")
                .buildAndExpand(user)
                .toUri();
        Long id = userService.registerUser(user);
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));
        RegistrationResponse response = new RegistrationResponse(id);
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/confirmRegistration")
    public ResponseEntity<RegistrationResponse> confirmRegistration (@RequestParam("token") String token) throws VerificationTokenDoesNotExistException, VerificationTokenExpiredException{
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            throw new VerificationTokenDoesNotExistException(token);
        }
        
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new VerificationTokenExpiredException(token);
        } 
        
        Long id = userService.enableUser(user);
        RegistrationResponse response = new RegistrationResponse(id); 
        return ResponseEntity.ok(response);
    }
}
