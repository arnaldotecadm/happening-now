package com.happeningnow.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "event")
public class Event {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
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
    private List<String> tagList = new ArrayList<>();
    @ManyToMany
    private List<Artist> artistList;
    @ManyToMany
    private List<Category> categoryList;
    @OneToMany
    private List<Comment> commentList;
}