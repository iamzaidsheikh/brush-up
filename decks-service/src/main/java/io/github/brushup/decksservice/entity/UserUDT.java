package io.github.brushup.decksservice.entity;

import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.Entity;

import lombok.Data;

@Entity
@Data
public class UserUDT {
    
    private UUID id;

    private String username;

    private String firstName;

    private String lastName;
}
