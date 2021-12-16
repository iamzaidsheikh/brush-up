package io.github.brushup.cardsservice.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class APIError {
    
    private HttpStatus status;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
   private LocalDateTime timestamp;

   private String message;
   
   private String debugMessage;

   private APIError() {
       timestamp = LocalDateTime.now();
   }

   public APIError(HttpStatus status) {
       this();
       this.status = status;
   }

   public APIError(HttpStatus status, Throwable ex) {
       this();
       this.status = status;
       this.message = "Unexpected error";
       this.debugMessage = ex.getLocalizedMessage();
   }

   public APIError(HttpStatus status, String message, Throwable ex) {
       this();
       this.status = status;
       this.message = message;
       this.debugMessage = ex.getLocalizedMessage();
   }
}
