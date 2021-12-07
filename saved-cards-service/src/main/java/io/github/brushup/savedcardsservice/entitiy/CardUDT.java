package io.github.brushup.savedcardsservice.entitiy;

import java.time.Instant;

import com.datastax.oss.driver.api.mapper.annotations.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CardUDT {
    
    private String id;

    private UserUDT author;

    private String front;

    private String back;

    private int numSaves;

    private Instant dateCreated;

    public CardUDT(Card card) {
        CardUDT cardUdt = new CardUDT();
        cardUdt.setId(card.getId());
        cardUdt.setAuthor(card.getAuthor());
        cardUdt.setFront(card.getFront());
        cardUdt.setBack(card.getBack());
        cardUdt.setNumSaves(card.getNumSaves());
        cardUdt.setDateCreated(card.getDateCreated());
    }
}
