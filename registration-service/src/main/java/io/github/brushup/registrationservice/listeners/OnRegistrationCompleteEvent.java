package io.github.brushup.registrationservice.listeners;

import org.springframework.context.ApplicationEvent;

import io.github.brushup.registrationservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent{

    private String appUrl;
    private User user;

    public OnRegistrationCompleteEvent(
      User user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }
    
}
