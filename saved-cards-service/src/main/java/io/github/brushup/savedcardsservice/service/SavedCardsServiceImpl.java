package io.github.brushup.savedcardsservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import io.github.brushup.savedcardsservice.dao.SavedCardsDao;
import io.github.brushup.savedcardsservice.entitiy.SavedCards;
import io.github.brushup.savedcardsservice.exceptions.NoSavedCardsFoundException;
import io.github.brushup.savedcardsservice.utils.ResourceId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavedCardsServiceImpl implements ISavedCardsService {

    private final SavedCardsDao dao;
    private final WebClient.Builder webClientBuilder;

    @Override
    public SavedCards getCards(String userId) {
        Optional<SavedCards> sco = dao.findById(userId);
        if (sco.isPresent()) {
            log.info("Getting saved cards for user {}", userId);
            return sco.get();
        } else {
            throw new NoSavedCardsFoundException(userId);
        }
    }

    @Override
    public List<String> addCards(List<String> cardIds, String userId) {
        SavedCards sc = getCards(userId);
        List<String> savedCardIds = sc.getSavedCards();
        List<String> cardsAdded = new ArrayList<>();
        cardIds.forEach(cardId -> {
            if (!savedCardIds.contains(cardId)) {
                try {
                    ResponseEntity<Void> getCardResponse = webClientBuilder.build()
                            .get()
                            .uri("http://cards-service/cards/" + cardId)
                            .retrieve()
                            .toBodilessEntity()
                            .block();
                    if (getCardResponse.getStatusCode().equals(HttpStatus.OK)) {
                        try {
                            ResponseEntity<Void> addUserResponse = webClientBuilder.build()
                                    .put()
                                    .uri("http://cards-service/cards/save")
                                    .header("userId", userId)
                                    .bodyValue(new ResourceId(cardId))
                                    .retrieve()
                                    .toBodilessEntity()
                                    .block();
                            if (addUserResponse.getStatusCode().equals(HttpStatus.CREATED)) {
                                savedCardIds.add(cardId);
                                cardsAdded.add(cardId);
                                log.info("Card {} saved", cardId);
                            }
                        } catch (WebClientResponseException e) {
                            log.error("Something went wrong. Could not add user to card");
                        }

                    }
                } catch (WebClientResponseException e) {
                    if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        log.error("Card {} does not exist", cardId);
                    } else {
                        log.error("Something went wrong. Could not verify card id");
                    }
                }
            } else {
                log.info("Card {} is already saved", cardId);
            }
            sc.setSavedCards(savedCardIds);
            dao.update(sc);
        });

        return cardsAdded;
    }

    @Override
    public List<String> removeCards(List<String> cardIds, String userId) {
        SavedCards sc = getCards(userId);
        List<String> savedCardIds = sc.getSavedCards();
        List<String> removedCards = new ArrayList<>();
        cardIds.forEach(cardId -> {
            if (savedCardIds.contains(cardId)) {
                savedCardIds.remove(cardId);
                removedCards.add(cardId);
                log.info("Card {} removed", cardId);
            } else {
                log.info("Card {} is not saved", cardId);
            }
            sc.setSavedCards(savedCardIds);
            dao.update(sc);
        });

        return removedCards;
    }

    @Override
    public String removeAll(String userId) {
        SavedCards sc = getCards(userId);
        sc.getSavedCards().clear();
        log.info("Cards removed for user {}", userId);
        dao.update(sc);

        return userId;
    }
}
