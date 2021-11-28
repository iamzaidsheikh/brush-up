package io.github.brushup.registrationservice.listeners;

import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import io.github.brushup.registrationservice.models.User;
import io.github.brushup.registrationservice.services.IUserService;
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

        String recipientAddress = user.getEmail();
        String subject = "Confirm Registration for Brush Up";
        String confirmationUrl = event.getAppUrl() + "/confirmRegistration?token=" + token;
        String message = "Hey there. Welcome to Brush Up. \nClick on the following link to activate your account";

        // Creating the email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8082" + confirmationUrl);
        mailSender.send(email);
        log.info("Verfication link sent to email: {}", recipientAddress);
    }
}
