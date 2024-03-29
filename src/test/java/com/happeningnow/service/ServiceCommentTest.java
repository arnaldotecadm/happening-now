package com.happeningnow.service;

import com.happeningnow.model.Comment;
import com.happeningnow.repository.CommentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

        Assertions.assertEquals(result.getComment(), comment.getComment());
        Assertions.assertEquals(1, size, "Quantity comments test");

        Assertions.assertFalse(comment.getTitle().isEmpty(), "Title test");

        Assertions.assertEquals(0, comment.getLikes(), "Like test");
        Assertions.assertEquals(0, comment.getDeslikes(), "Deslike test");
    }

    @Test
    @DisplayName("Must find comment by id")
    public void findById(){
        serviceComment.save(comment);

        var result = serviceComment.findById(comment.getId());

        Assertions.assertEquals(result.get().getId(), comment.getId());
    }

    @Test
    @DisplayName("Must find all comments")
    public void listComment(){
        serviceComment.save(comment);

        Page<Comment>listOfComment =
                serviceComment.list(PageRequest.of(0,50, Sort.Direction.ASC, "Title"));

                Assertions.assertFalse(listOfComment.getContent().isEmpty());
        Assertions.assertTrue(listOfComment.stream().anyMatch(l -> l.getTitle().equals(comment.getTitle())));
    }

    @Test
    @DisplayName("Must delete comment")
    public void deleteById(){
        serviceComment.save(comment);

        Page<Comment>listOfComment =
                serviceComment.list(PageRequest.of(0,50, Sort.Direction.ASC, "Title"));

                Assertions.assertFalse(listOfComment.getContent().isEmpty());

                serviceComment.deleteById(comment.getId());

        listOfComment =
                serviceComment.list(PageRequest.of(0,50, Sort.Direction.ASC,"Title"));

                Assertions.assertTrue(listOfComment.getContent().isEmpty());
    }
}