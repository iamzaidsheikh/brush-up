package io.github.brushup.savedcardsservice.entitiy;

import com.datastax.oss.driver.api.mapper.annotations.Entity;

import lombok.Data;

@Entity
@Data
public class UserUDT {
    
    private String id;

    private String username;

    private String firstName;

    private String lastName;
}
