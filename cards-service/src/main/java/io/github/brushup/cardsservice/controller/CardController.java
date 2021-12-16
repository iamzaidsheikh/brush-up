package io.github.brushup.cardsservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.brushup.cardsservice.entity.Card;
import io.github.brushup.cardsservice.exception.InvalidIdException;
import io.github.brushup.cardsservice.service.ICardService;
import io.github.brushup.cardsservice.utils.SaveCard;
import io.github.brushup.cardsservice.utils.GetCardResponseBody;
import io.github.brushup.cardsservice.utils.IdResponseBody;
import io.github.brushup.cardsservice.utils.UpdateCard;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final ICardService cardService;

    private boolean isValidId(String id) {
        try {
            UUID.fromString(id);

            return true;
        } catch (IllegalArgumentException e) {

            return false;
        }
    }

    @GetMapping(value = "/{cardId}")
    public ResponseEntity<GetCardResponseBody> getCard(@PathVariable String cardId) {
        if (!isValidId(cardId)) {
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

    @PostMapping()
    public ResponseEntity<List<IdResponseBody>> saveCards(
            @RequestBody @NotEmpty(message = "List of cards cannot be empty.") List<@Valid SaveCard> cards,
            HttpServletRequest request) {
        String userId = request.getHeader("userId");
        List<String> cardIds = cardService.saveCards(cards, userId);
        List<IdResponseBody> body = new ArrayList<>();
        cardIds.stream().forEach(cardId -> {
            body.add(new IdResponseBody(cardId));
        });

        return ResponseEntity.ok().body(body);
    }

    @PutMapping()
    public ResponseEntity<IdResponseBody> updateCard(
            @RequestBody @Valid UpdateCard updateCard,
            HttpServletRequest request) {
            String userId = request.getHeader("userId");
            String cardId = cardService.updateCard(updateCard, userId);
            IdResponseBody body = new IdResponseBody(cardId);
            
            return ResponseEntity.ok().body(body);
    }
}
