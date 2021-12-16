package io.github.brushup.cardsservice.service;

import io.github.brushup.cardsservice.entity.Card;
import io.github.brushup.cardsservice.utils.CardUtil;

public interface ICardService {
    String saveCard(CardUtil card, String userId);

    Card getCard(String cardId);
}
