package ru.practicum.base.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.practicum.base.dto.location.Location;
import ru.practicum.base.utils.State;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "EVENTS")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 2000)
    @NotNull
    @Column(name = "ANNOTATION", nullable = false, length = 2000)
    private String annotation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Column(name = "CONFIRMED_REQUESTS")
    private Long confirmedRequests;

    @NotNull
    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @Size(max = 7000)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 7000)
    private String description;

    @NotNull
    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "INITIATOR_ID", nullable = false)
    private User initiator;

    @AttributeOverrides(value = {
            @AttributeOverride(name = "lat", column = @Column(name = "LAT")),
            @AttributeOverride(name = "lon", column = @Column(name = "LON"))
    })
    private Location location;

    @NotNull
    @Column(name = "PAID", nullable = false)
    private Boolean paid = false;

    @Column(name = "PARTICIPANT_LIMIT")
    private Long participantLimit;

    @Column(name = "PUBLISHED_ON")
    private LocalDateTime publishedOn;

    @Column(name = "REQUEST_MODERATION")
    private Boolean requestModeration;

    @Size(max = 32)
    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false, length = 32)
    private State state;

    @Size(max = 128)
    @NotNull
    @Column(name = "TITLE", nullable = false, length = 128)
    private String title;

    @Column(name = "VIEWS")
    private Long views;

}