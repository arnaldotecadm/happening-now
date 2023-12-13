package com.happeningnow.Model;

import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @UuidGenerator
    @Column(name = "comment_id")
    private UUID commentId;
    private String title;
    private String comment;
    @Column(name = "user_creation")
    private UUID userCreation;
    private int likes;
    private int deslikes;

    public Comments() {
    }

    public Comments(String title, String comment, int likes, int deslikes) {
        this.title = title;
        this.comment = comment;
        this.likes = likes;
        this.deslikes = deslikes;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDeslikes() {
        return deslikes;
    }

    public void setDeslikes(int deslikes) {
        this.deslikes = deslikes;
    }
}
