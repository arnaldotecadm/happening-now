package com.happeningnow.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "event")
public class Event {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    private boolean status;
    private boolean payed;
    @Column(name = "web_page")
    private String webPage;
    private byte[] thumbnail;
    @ElementCollection()
    private List<String> tags = new ArrayList<>();
    @ManyToMany
    private List<Artist> artist;
    @ManyToMany
    private List<Category> category;
    @ManyToOne
    private Location location;
    @OneToMany
    private List<Comment> comments;
}
