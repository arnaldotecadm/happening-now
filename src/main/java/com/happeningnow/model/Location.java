package com.happeningnow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "location")
public class Location {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String adrress;
    @OneToMany
    private List<Event> eventList;
}