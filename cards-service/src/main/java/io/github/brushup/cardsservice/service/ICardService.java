package io.github.brushup.cardsservice.service;

import java.util.List;

import io.github.brushup.cardsservice.entity.Card;
import io.github.brushup.cardsservice.utils.SaveCard;
import io.github.brushup.cardsservice.utils.UpdateCard;

public interface ICardService {
    Card getCard(String cardId);

    List<String> saveCards(List<SaveCard> cards, String userId);

    String updateCard(UpdateCard updateCard, String userId);
}
