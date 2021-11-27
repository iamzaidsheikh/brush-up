package io.github.brushup.registrationservice.exceptions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RegistrationError {
    
    private HttpStatus status;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
   private LocalDateTime timestamp;

   private String message;
   
   private String debugMessage;

   private RegistrationError() {
       timestamp = LocalDateTime.now();
   }

   public RegistrationError(HttpStatus status) {
       this();
       this.status = status;
   }

   public RegistrationError(HttpStatus status, Throwable ex) {
       this();
       this.status = status;
       this.message = "Unexpected error";
       this.debugMessage = ex.getLocalizedMessage();
   }

   public RegistrationError(HttpStatus status, String message, Throwable ex) {
       this();
       this.status = status;
       this.message = message;
       this.debugMessage = ex.getLocalizedMessage();
   }
}
