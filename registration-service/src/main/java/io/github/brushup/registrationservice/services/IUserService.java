package io.github.brushup.registrationservice.services;

import io.github.brushup.registrationservice.models.User;
import io.github.brushup.registrationservice.models.VerificationToken;

public interface IUserService {
    
    String registerUser(User user);

    User getUser(String token);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    String enableUser(User user);

    VerificationToken generateNewVerificationToken(String existingToken);
}
