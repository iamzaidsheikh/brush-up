package io.github.brushup.registrationservice.services;

import io.github.brushup.registrationservice.models.User;

public interface IUserService {
    
    Long registerUser(User user);
}
