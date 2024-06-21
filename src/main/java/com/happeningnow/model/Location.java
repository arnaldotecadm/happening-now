package com.happeningnow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "location")
public class Location extends BaseEntity{

    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String adrress;
    @OneToMany
    private List<Event> eventList;

    public Location(UUID id, String name, String description, String adrress, List<Event> eventList) {
        super(id);
        this.name = name;
        this.description = description;
        this.adrress = adrress;
        this.eventList = eventList;
    }
}