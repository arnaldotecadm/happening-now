package com.happeningnow.model;

import com.happeningnow.enuns.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "event")
public class Event extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(name = "long_description", length = 5000)
    private String longDescription;
    @Column(name = "short_description", length = 150)
    private String shortDescription;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum statusEnum;
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
    @ManyToMany
    private List<Organizer> organizerList;
    @OneToMany
    private List<Comment> commentList;
}