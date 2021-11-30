package io.github.brushup.registrationservice.utils;

import org.springframework.mail.SimpleMailMessage;

public class SendVerificationEmail {
    private final String recipientAddress;
    private final String subject; 
    private final String baseURL;
    private final String path; 
    private final String message;
    
    public SendVerificationEmail(String recipientAddress, String subject, String baseURL, String path, String message) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.baseURL = baseURL;
        this.path = path;
        this.message = message;
    }
    
    public SimpleMailMessage getEmail() {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + baseURL + path);

        return email;
    }
}
