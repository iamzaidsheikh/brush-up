package io.github.brushup.cardsservice.utils;


import io.github.brushup.cardsservice.validation.Back;
import io.github.brushup.cardsservice.validation.Front;
import lombok.Data;

@Data
public class SaveCard {
   
    @Front
    private String front;

    @Back
    private String back;

}
