package io.github.brushup.savedcardsservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.brushup.savedcardsservice.dao.SavedCardsDao;
import io.github.brushup.savedcardsservice.entitiy.CardUDT;
import io.github.brushup.savedcardsservice.entitiy.SavedCards;
import io.github.brushup.savedcardsservice.exceptions.NoSavedCardsFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavedCardsServiceImpl implements ISavedCardsService {

    private final SavedCardsDao dao;

    @Override
    public SavedCards getCards(String id) {
        Optional<SavedCards> sco = dao.findById(id);
        if (sco.isPresent()) {
            log.info("Getting saved cards for user {}", id);
            return sco.get();
        } else {
            throw new NoSavedCardsFoundException(id);
        }
    }

    @Override
    public String addCard(CardUDT card, String id) {
        try {
            SavedCards sc = getCards(id);
            if (!sc.getCards().contains(card)) {
                sc.getCards().add(card);
                dao.update(sc);
            } else {
                log.info("Card {} already exists", card.getId());
            }
        } catch (NoSavedCardsFoundException e) {
            SavedCards sc = new SavedCards();
            sc.setId(id);
            sc.getCards().add(card);
            dao.save(sc);
        }

        log.info("Card {} added to user {}", card.getId(), id);
        return card.getId();
    }

    @Override
    public String removeCard(CardUDT card, String id) {
        SavedCards sc = getCards(id);
        if (sc.getCards().contains(card)) {
            sc.getCards().remove(card);
            dao.update(sc);
        }
        log.info("Card {} removed", card.getId());

        return card.getId();
    }

    @Override
    public String addCards(List<CardUDT> cards, String id) {
        SavedCards sc = getCards(id);
        cards.stream().forEach(card -> {
            if (!sc.getCards().contains(card)) {
                sc.getCards().add(card);
            }
        });
        log.info("Cards saved for user {}", id);
        dao.update(sc);

        return id;
    }

    @Override
    public String removeCards(List<CardUDT> cards, String id) {
        SavedCards sc = getCards(id);
        cards.stream().forEach(card -> {
            if (sc.getCards().contains(card)) {
                sc.getCards().remove(card);
            }
        });
        log.info("Cards removed for user {}", id);
        dao.update(sc);

        return id;
    }

    @Override
    public String removeAll(String id) {
        SavedCards sc = getCards(id);
        sc.getCards().clear();
        log.info("Cards removed for user {}", id);
        dao.update(sc);

        return id;
    }

}
