package ru.practicum.base.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Table(name = "COMPILATIONS")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 128)
    @NotNull
    @Column(name = "TITLE", nullable = false, length = 128)
    private String title;

    @NotNull
    @Column(name = "PINNED", nullable = false)
    private Boolean pinned = false;

    @ManyToMany
    @JoinTable(name = "EVENTS_COMPILATIONS",
            joinColumns = @JoinColumn(name = "COMPILATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
    private List<Event> events;
}