package io.github.brushup.registrationservice.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.brushup.registrationservice.models.User;
import io.github.brushup.registrationservice.services.IUserService;
import io.github.brushup.registrationservice.utils.RegistrationResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final IUserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid User user, HttpServletRequest request) {

        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath("/register")
                .buildAndExpand(user)
                .toUri();
        Long id = userService.registerUser(user);
        RegistrationResponse response = new RegistrationResponse();
        response.setUserId(id);
        return ResponseEntity.created(location).body(response);
    }

}
