package io.github.brushup.cardsservice.utils;

import io.github.brushup.cardsservice.validation.Back;
import io.github.brushup.cardsservice.validation.Front;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUtil {
   
    @Front
    private String front;

    @Back
    private String back;

}
