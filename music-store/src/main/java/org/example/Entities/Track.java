package org.example.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "Track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRACK_ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DURATION")
    private LocalTime duration;

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    private Album album;
}
