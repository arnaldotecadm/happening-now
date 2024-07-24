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
@Table(name = "artist")
public class Artist extends BaseEntity{

    @Column(name = "name")
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "artistList")
    private List<Event> eventList;
 }