package com.happeningnow.service;

import com.happeningnow.model.Comment;
import com.happeningnow.repository.CommentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class ServiceCommentTest {

    @Autowired
    private ServiceComment serviceComment;

    @Autowired
    private CommentRepository commentRepository;

    private Comment comment;

    @BeforeEach
    public void creatComment(){
       UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
       comment = new Comment(id,"Alex Sander","Are developer", UUID.randomUUID(),0,0);
    }

    @AfterEach
    public void setUp(){
        this.commentRepository.deleteAll();
    }

    @Test
    @DisplayName("Must save comments")
    public void save(){
        var result = serviceComment.save(comment);

        var size = commentRepository.findAll().size();

        var deslike = commentRepository.findAll().size();


        Assertions.assertEquals(result.getComment(), comment.getComment());
        Assertions.assertEquals(1, size, "Quantity comments test");

        Assertions.assertFalse(comment.getTitle().isEmpty(), "Title test");

        Assertions.assertEquals(0, comment.getLikes(), "Likes test");


    }



}
