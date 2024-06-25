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
@Table(name = "category")
public class Category extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "categoryList")
    private List<Event> eventList;
}