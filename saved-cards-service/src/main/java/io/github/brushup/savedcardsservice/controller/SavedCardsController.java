package io.github.brushup.savedcardsservice.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.brushup.savedcardsservice.entitiy.SavedCards;
import io.github.brushup.savedcardsservice.exceptions.InvalidIdException;
import io.github.brushup.savedcardsservice.service.ISavedCardsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards/saved")
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
        if(!isValidId(userId)) {
            throw new InvalidIdException(userId);
        }
        SavedCards sc = savedCardsService.getCards(userId);
        
        return ResponseEntity.ok(sc);
    }
}
