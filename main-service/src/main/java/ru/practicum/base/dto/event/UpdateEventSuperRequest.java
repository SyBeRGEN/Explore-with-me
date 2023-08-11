package ru.practicum.base.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.base.dto.location.Location;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Суперкласс с общими полями UpdateEventAdminRequest, UpdateEventUserRequest:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateEventSuperRequest {
    @Size(min = 20, max = 2000)
    private String annotation;
    private Long category;
    @Size(min = 20, max = 7000)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    private Long participantLimit;
    private Boolean requestModeration;
    @Size(min = 3, max = 120)
    private String title;
}
