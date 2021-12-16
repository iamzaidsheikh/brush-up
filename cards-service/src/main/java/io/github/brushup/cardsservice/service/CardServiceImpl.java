package io.github.brushup.cardsservice.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.brushup.cardsservice.dao.CardDao;
import io.github.brushup.cardsservice.entity.Card;
import io.github.brushup.cardsservice.entity.UserUDT;
import io.github.brushup.cardsservice.exception.CardNotFoundException;
import io.github.brushup.cardsservice.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements ICardService {

    private final CardDao dao;

    @Override
    public String saveCard(CardUtil cu, String userId) {
        //Get user info from user profile service
        UserUDT author = new UserUDT();
        author.setId(userId);
        author.setFirstName("Zaid");
        author.setLastName("Sheikh");
        author.setUsername("zaid");

        Card card = new Card();
        card.setId(UUID.randomUUID().toString());
        card.setAuthor(author);
        card.setFront(cu.getFront());
        card.setBack(cu.getBack());
        card.setDateCreated(Instant.now());
        card.setNumSaves(0);
        card.setSavedBy(new ArrayList<UserUDT>());
        dao.save(card);
        String cardId = card.getId();
        log.info("Card {} saved", cardId);

        return cardId;
    }

    @Override
    public Card getCard(String cardId) {
        Optional<Card> cardOpt = dao.findById(cardId);
        if(cardOpt.isPresent()) {
            log.info("Getting card {}", cardId);

            return cardOpt.get();
        }else {
            throw new CardNotFoundException(cardId);
        }
    }
    
}

