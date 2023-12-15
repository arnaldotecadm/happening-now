package com.happeningnow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data

@Entity
@Table(name = "organizer")
public class Organizer {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String address;
    @ManyToMany(mappedBy = "organizerList")
    private List<Event> eventList;
}