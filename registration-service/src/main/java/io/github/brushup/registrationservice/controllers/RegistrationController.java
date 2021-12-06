package io.github.brushup.registrationservice.controllers;

import java.net.URI;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import io.github.brushup.registrationservice.utils.SendVerificationEmail;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final IUserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final JavaMailSender mailSender;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid User user,
            HttpServletRequest request) {
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath("/register")
                .buildAndExpand(user)
                .toUri();
        String id = userService.registerUser(user);
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appUrl));
        RegistrationResponse response = new RegistrationResponse(id);
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/confirmRegistration")
    public ResponseEntity<RegistrationResponse> confirmRegistration(@RequestParam("token") String token)
            throws VerificationTokenDoesNotExistException, VerificationTokenExpiredException {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            throw new VerificationTokenDoesNotExistException(token);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new VerificationTokenExpiredException(token);
        }

        String id = userService.enableUser(user);
        RegistrationResponse response = new RegistrationResponse(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirmRegistration/resend")
    public ResponseEntity<RegistrationResponse> resendRegistrationToken(
            HttpServletRequest request, @RequestParam("token") String existingToken) {
        VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
        User user = userService.getUser(newToken.getToken());

        String appUrl = "http://" + request.getServerName() +
                ":" + request.getServerPort();

        SimpleMailMessage email = new SendVerificationEmail(user.getEmail(),
                "Confirm Registration for Brush Up",
                appUrl,
                "/confirmRegistration?token=" + newToken.getToken(),
                "Hey there. Welcome to Brush Up. \nClick on the following link to activate your account")
                        .getEmail();

        mailSender.send(email);
        RegistrationResponse response = new RegistrationResponse(user.getId());
        return ResponseEntity.ok(response);
    }
}
