package io.github.brushup.registrationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brushup.registrationservice.models.User;
import io.github.brushup.registrationservice.models.VerificationToken;

public interface VerificationTokenRepo 
  extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
