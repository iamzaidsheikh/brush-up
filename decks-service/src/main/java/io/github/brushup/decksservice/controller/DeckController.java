package io.github.brushup.decksservice.controller;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.brushup.decksservice.entity.Deck;
import io.github.brushup.decksservice.service.DeckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/decks")
@RequiredArgsConstructor
@Slf4j
public class DeckController {
    
    private final DeckService deckService;

    @GetMapping(value = "/{deckId}")
    public ResponseEntity<Deck> getDeck(@PathVariable UUID deckId) {
        Optional<Deck> optionalDeck = deckService.findDeckById(deckId);
        if(optionalDeck.isPresent()) {
            log.info("Deck: {} found", deckId);
            return ResponseEntity.ok().body(optionalDeck.get());
        }else {
            log.error("Deck: {} not found", deckId);
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<UUID> saveDeck(@RequestBody Deck deck, HttpServletRequest request) {
        UUID deckId = deckService.createDeck(deck);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath("/decks/save/" + deckId.toString())
                .buildAndExpand(deckId)
                .toUri();
        log.info("Deck: {} created", deckId);
                
        return ResponseEntity.created(location).body(deckId);
    }
}
