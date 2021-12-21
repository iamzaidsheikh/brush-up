package io.github.brushup.cardsservice.utils;

import io.github.brushup.cardsservice.validation.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceId {

    @UUID
    private String id;
}
