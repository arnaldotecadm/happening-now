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
@Table(name = "organizer")

public class Organizer extends BaseEntity{

    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String address;
    @ManyToMany(mappedBy = "organizerList")
    private List<Event> eventList;

    public Organizer(UUID id, String name, String description, String address, List<Event> eventList) {
        super(id);
        this.name = name;
        this.description = description;
        this.address = address;
        this.eventList = eventList;
    }
}