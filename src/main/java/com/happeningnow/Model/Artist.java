package com.happeningnow.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @UuidGenerator
    @Column(name = "artist_id")
    private UUID artistId; // one to many
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "artist")
    private List<Event> event;
    public Artist() {
    }

    public Artist(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public UUID getArtistId() {
        return artistId;
    }

    public void setArtistId(UUID artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 }
