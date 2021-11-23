package io.github.brushup.decksservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.brushup.decksservice.dao.DeckDao;
import io.github.brushup.decksservice.entity.Card;
import io.github.brushup.decksservice.entity.Deck;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeckService {
    
    private final DeckDao deckDao;
    private final CardService cardService;

    public UUID createDeck(Deck deck) {
        deckDao.save(deck);
        
        return deck.getId();
    }

    public Optional<Deck> findDeckById(UUID id) {
        return deckDao.findById(id);
    }

    //TODO : Change this to return a UUID
    public boolean addCardToDeck(UUID deckId, Card card) {
        Optional<Deck> optionalDeck = findDeckById(deckId);
        if(optionalDeck.isPresent()) {
            Deck deck = optionalDeck.get();
            List<Card> cards = deck.getCards();
            if(!cards.contains(card)) {
                cards.add(card);
                deck.setNumCards(deck.getNumCards() + 1);
            }
            if(!cardService.findCardById(card.getId()).isPresent()) {
                cardService.createCard(card);
            }

            return true;
        }else {
            return false;
        }
    }
    
    //TODO : Change this to return a UUID
    public boolean deleteCardFromDeck(UUID deckId, Card card) {
        Optional<Deck> optionalDeck = findDeckById(deckId);
        if(optionalDeck.isPresent()) {
            Deck deck = optionalDeck.get();
            List<Card> cards = deck.getCards();
            if(cards.contains(card)) {
                cards.remove(card);
                deck.setNumCards(deck.getNumCards() - 1);
            }
            
            return true;
        }else {
            return false;
        }
    }
}
