package io.github.brushup.decksservice.service;

import java.util.UUID;

import com.datastax.oss.driver.shaded.guava.common.base.Optional;

import org.springframework.stereotype.Service;

import io.github.brushup.decksservice.dao.CardDao;
import io.github.brushup.decksservice.entity.Card;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
    
    private final CardDao cardDao;

    public UUID createCard(Card card) {
        cardDao.save(card);
        
        return card.getId();
    }

    public Optional<Card> findCardById(UUID id) {
        return cardDao.findById(id);
    }
}
