package io.github.brushup.savedcardsservice.service;

import java.util.List;

import io.github.brushup.savedcardsservice.entitiy.CardUDT;
import io.github.brushup.savedcardsservice.entitiy.SavedCards;

public interface ISavedCardsService {
    
    SavedCards getCards(String id);

    String addCard(CardUDT card, String id);

    String removeCard(CardUDT card, String id);

    String addCards(List<CardUDT> cards, String id);

    String removeCards(List<CardUDT> cards, String id);

    String removeAll(String id);
}
