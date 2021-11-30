package io.github.brushup.registrationservice.listeners;

import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import io.github.brushup.registrationservice.models.User;
import io.github.brushup.registrationservice.services.IUserService;
import io.github.brushup.registrationservice.utils.SendVerificationEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final IUserService userService;
    private final JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString(); // Generating token
        userService.createVerificationToken(user, token);
        SimpleMailMessage email = new SendVerificationEmail(user.getEmail(),
                                                                "Confirm Registration for Brush Up",
                                                                "http://localhost:8082",
                                                                "/confirmRegistration?token=" + token, 
                                                                "Hey there. Welcome to Brush Up. \nClick on the following link to activate your account")
                                                                .getEmail();
        mailSender.send(email);
        log.info("Verfication link sent to email: {}", user.getEmail());
    }
}
