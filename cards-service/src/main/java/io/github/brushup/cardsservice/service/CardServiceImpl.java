package io.github.brushup.cardsservice.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.brushup.cardsservice.dao.CardDao;
import io.github.brushup.cardsservice.entity.Card;
import io.github.brushup.cardsservice.entity.UserUDT;
import io.github.brushup.cardsservice.exception.CannotUpdateCardException;
import io.github.brushup.cardsservice.exception.CardNotFoundException;
import io.github.brushup.cardsservice.utils.SaveCard;
import io.github.brushup.cardsservice.utils.UpdateCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements ICardService {

    private final CardDao dao;

    @Override
    public Card getCard(String cardId) {
        Optional<Card> cardOpt = dao.findById(cardId);
        if (cardOpt.isPresent()) {
            log.info("Getting card {}", cardId);

            return cardOpt.get();
        } else {
            throw new CardNotFoundException(cardId);
        }
    }

    @Override
    public List<String> saveCards(List<SaveCard> cards, String userId) {
        // Get user info from user profile service
        UserUDT author = new UserUDT();
        author.setId(userId);
        author.setFirstName("Zaid");
        author.setLastName("Sheikh");
        author.setUsername("zaid");

        List<String> cardIds = new ArrayList<>();
        cards.stream().forEach(card -> {
            Card newCard = new Card();
            newCard.setId(UUID.randomUUID().toString());
            newCard.setAuthor(author);
            newCard.setFront(card.getFront());
            newCard.setBack(card.getBack());
            newCard.setDateCreated(Instant.now());
            newCard.setNumSaves(0);
            newCard.setSavedBy(new ArrayList<UserUDT>());
            dao.save(newCard);
            cardIds.add(newCard.getId());
            log.info("Card {} saved", newCard.getId());
        });

        return cardIds;
    }

    @Override
    public String updateCard(UpdateCard updateCard, String userId) {
        Card card = getCard(updateCard.getCardId());
        if(!userId.matches(card.getAuthor().getId())) {
            throw new CannotUpdateCardException(card.getId(), "User is not the author of this card");
        }
        if(card.getNumSaves() != 0) {
            throw new CannotUpdateCardException(card.getId(), "This card has been saved by other users");
        }
        card.setFront(updateCard.getFront());
        card.setBack(updateCard.getBack());
        card.setDateCreated(Instant.now());
        dao.update(card);
        log.info("Card {} updated", card.getId());

        return card.getId();
    }
}
