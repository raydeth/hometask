package com.github.raydeth.model;

import com.github.raydeth.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long eventId;

    @Column(name = "title")
    private String title;

    @Column(name = "place")
    private String place;

    @Column(name = "speaker")
    private String speaker;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
