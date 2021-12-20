package io.github.brushup.savedcardsservice.service;

import java.util.List;

import io.github.brushup.savedcardsservice.entitiy.SavedCards;

public interface ISavedCardsService {
    
    SavedCards getCards(String userId);

    List<String> addCards(List<String> cardIds, String userId);

    List<String> removeCards(List<String> cardIds, String userId);

    String removeAll(String userId);
}
