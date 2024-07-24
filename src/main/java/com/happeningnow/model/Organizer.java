package com.happeningnow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
}