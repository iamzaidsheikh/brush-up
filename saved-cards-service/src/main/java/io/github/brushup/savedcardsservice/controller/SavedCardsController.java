package io.github.brushup.savedcardsservice.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.brushup.savedcardsservice.entitiy.SavedCards;
import io.github.brushup.savedcardsservice.exceptions.EmptyListException;
import io.github.brushup.savedcardsservice.exceptions.InvalidIdException;
import io.github.brushup.savedcardsservice.exceptions.MissingUserIdHeaderException;
import io.github.brushup.savedcardsservice.service.ISavedCardsService;
import io.github.brushup.savedcardsservice.utils.CardIds;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SavedCardsController {
    
    private final ISavedCardsService savedCardsService;

    private boolean isValidId(String id) {
        try {
            UUID.fromString(id);
            
            return true;
        }catch(IllegalArgumentException e) {
            
            return false;
        }
    }

    @GetMapping
    public ResponseEntity<SavedCards> getSavedCards(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(userId == null || userId.isEmpty()) {
            throw new MissingUserIdHeaderException();
        }
        if(!isValidId(userId)) {
            throw new InvalidIdException(userId);
        }
        SavedCards sc = savedCardsService.getCards(userId);
        
        return ResponseEntity.ok(sc);
    }

    @PutMapping
    public ResponseEntity<CardIds> addCards(@RequestBody List<String> cardIds, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(userId == null || userId.isEmpty()) {
            throw new MissingUserIdHeaderException();
        }
        if(!isValidId(userId)) {
            throw new InvalidIdException(userId);
        }
        if(cardIds.isEmpty()){
            throw new EmptyListException();
        }
        List<String> cardsAdded = savedCardsService.addCards(cardIds, userId);
        CardIds body = new CardIds(cardsAdded);
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .buildAndExpand(userId)
        .toUri();
        
        return ResponseEntity.created(location).body(body);
    }

    @DeleteMapping(value = "/{cardId}")
    public ResponseEntity<Void> removeCard(@PathVariable String cardId, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(userId == null || userId.isEmpty()) {
            throw new MissingUserIdHeaderException();
        }
        if(!isValidId(userId)) {
            throw new InvalidIdException(userId);
        }
        if(!isValidId(cardId)){
            throw new InvalidIdException(cardId);
        }
        savedCardsService.removeCard(cardId, userId);
        
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/clear")
    public ResponseEntity<Void> removeAll(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(userId == null || userId.isEmpty()) {
            throw new MissingUserIdHeaderException();
        }
        if(!isValidId(userId)) {
            throw new InvalidIdException(userId);
        }
        savedCardsService.removeAll(userId);

        return ResponseEntity.noContent().build();
    }
}
