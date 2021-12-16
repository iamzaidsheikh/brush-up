package io.github.brushup.cardsservice.utils;

import java.time.Instant;

import io.github.brushup.cardsservice.entity.UserUDT;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetCardResponseBody {
    private final String id;

    private final UserUDT author;

    private final String front;

    private final String back;

    private final int numSaves;

    private final Instant dateCreated;
}
