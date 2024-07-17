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
@Table(name = "artist")
public class Artist extends BaseEntity{

    @Column(name = "name")
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "artistList")
    private List<Event> eventList;
 }