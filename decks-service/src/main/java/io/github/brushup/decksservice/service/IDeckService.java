package io.github.brushup.decksservice.service;

import io.github.brushup.decksservice.entity.Deck;
import io.github.brushup.decksservice.utils.CreateDeck;

public interface IDeckService {

    Deck getDeck(String deckId);

    String createDeck(CreateDeck deckData, String userId);
}
