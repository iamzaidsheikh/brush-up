package io.github.brushup.cardsservice.controller;

import java.net.URI;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.brushup.cardsservice.entity.Card;
import io.github.brushup.cardsservice.exception.InvalidIdException;
import io.github.brushup.cardsservice.service.ICardService;
import io.github.brushup.cardsservice.utils.CardUtil;
import io.github.brushup.cardsservice.utils.GetCardResponseBody;
import io.github.brushup.cardsservice.utils.IdResponseBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    
    private final ICardService cardService;

    private boolean isValidId(String id) {
        try {
            UUID.fromString(id);
            
            return true;
        }catch(IllegalArgumentException e) {
            
            return false;
        }
    }

    @GetMapping(value = "/{cardId}")
    public ResponseEntity<GetCardResponseBody> getCard(@PathVariable String cardId) {
        if(!isValidId(cardId)) {
            throw new InvalidIdException(cardId);
        }
        Card card = cardService.getCard(cardId);
        GetCardResponseBody body = new GetCardResponseBody(card.getId(), 
        card.getAuthor(), 
        card.getFront(), 
        card.getBack(), 
        card.getNumSaves(), 
        card.getDateCreated());

        return ResponseEntity.ok(body);
    }

    @PostMapping("/save")
    public ResponseEntity<IdResponseBody> saveCard(@RequestBody @Valid CardUtil card, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        String cardId = cardService.saveCard(card, userId);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath("/cards/save/" + cardId.toString())
                .buildAndExpand(cardId)
                .toUri();
        IdResponseBody body = new IdResponseBody(cardId); 
                
        return ResponseEntity.created(location).body(body);
    }
}

