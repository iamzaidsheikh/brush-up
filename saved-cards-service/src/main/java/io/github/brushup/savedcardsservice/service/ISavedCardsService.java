package io.github.brushup.savedcardsservice.service;

import java.util.List;

import io.github.brushup.savedcardsservice.entitiy.SavedCards;

public interface ISavedCardsService {
    
    SavedCards getCards(String userId);

    List<String> addCards(List<String> cardIds, String userId);

    void removeCard(String cardId, String userId);

    void removeAll(String userId);
}
