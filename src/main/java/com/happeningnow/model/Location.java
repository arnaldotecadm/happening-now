package com.happeningnow.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "location")
public class Location extends BaseEntity{

    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String address;
    @OneToMany
    private List<Event> eventList;
}