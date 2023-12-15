package com.happeningnow.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @UuidGenerator
    private UUID id;
    private String title;
    private String comment;
    @Column(name = "user_creation")
    private UUID userCreation;
    private int likes;
    private int deslikes;
}
