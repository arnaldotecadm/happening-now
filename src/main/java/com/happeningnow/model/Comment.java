package com.happeningnow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    private String title;
    @Column(nullable = false, name = "comment_text")
    private String commentText;
    @Column(name = "user_creation")
    private UUID userCreation;
    private int likes;
    private int dislikes;
}