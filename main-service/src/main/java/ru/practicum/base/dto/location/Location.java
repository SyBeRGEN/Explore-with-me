package ru.practicum.base.dto.location;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * Широта и долгота места проведения события:
 * lat -> Широта
 * lon -> Долгота
 * Класс является Embeddable (встраиваемым), для дальнейшего использования в Event
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Location {
    private Float lat;
    private Float lon;
}