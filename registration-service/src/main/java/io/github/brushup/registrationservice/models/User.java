package io.github.brushup.registrationservice.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.github.brushup.registrationservice.validation.Password;
import io.github.brushup.registrationservice.validation.UUID;
import io.github.brushup.registrationservice.validation.Username;
import io.github.brushup.registrationservice.validation.ValidEmail;
import lombok.Data;

@Entity
@Data
public class User {
    
    @Id
    @NotEmpty
    @NotEmpty
    @UUID
    private String id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @Username
    private String username;

    @NotNull
    @NotEmpty
    @Password
    private String password;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    private boolean enabled;

    public User() {
        super();
        this.enabled=false; 
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
