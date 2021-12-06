package io.github.brushup.registrationservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.github.brushup.registrationservice.validation.UUID;
import lombok.Data;

@Entity
@Data
public class Role {
    
    @Id
    @NotNull
    @NotEmpty
    @UUID
    private String id;
    
    @NotNull
    @NotEmpty
    private String roleName;

}
