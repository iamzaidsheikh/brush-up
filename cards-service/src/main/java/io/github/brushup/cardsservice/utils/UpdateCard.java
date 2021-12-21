package io.github.brushup.cardsservice.utils;

import io.github.brushup.cardsservice.validation.Back;
import io.github.brushup.cardsservice.validation.Front;
import io.github.brushup.cardsservice.validation.UUID;
import lombok.Data;

@Data
public class UpdateCard {

    @UUID
    private String cardId;

    @Front
    private String front;

    @Back
    private String back;
}
