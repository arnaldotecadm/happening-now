package com.happeningnow.controller;

import com.happeningnow.model.Comment;
import com.happeningnow.repository.CommentRepository;
import com.happeningnow.util.CustomPageImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ControllerCommentTest {

    @Autowired
    private TestRestTemplate resTemplate;

    @Autowired
    private CommentRepository commentRepository;

    private Comment comment1;

    private Comment comment2;

    @AfterEach
    public void setUp(){
        this.commentRepository.deleteAll();
    }

    @Test
    @DisplayName("This controller method should save a comment")
    public void save(){
        comment1 = new Comment(
                "Good", "good event", UUID.randomUUID(), 0, 0
        );

        HttpEntity<Comment> saveRequest = new HttpEntity<>(comment1);
        ResponseEntity<Comment> responseEntity = resTemplate.exchange("/comment",
                HttpMethod.POST,
                saveRequest,
                Comment.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertEquals("Good", responseEntity.getBody().getTitle());
        Assertions.assertEquals("good event", responseEntity.getBody().getComment());
        Assertions.assertEquals(0, responseEntity.getBody().getLikes());
        Assertions.assertEquals(0, responseEntity.getBody().getDislikes());

        Optional<Comment> responseId = this.commentRepository.findById(responseEntity.getBody().getId());
        Assertions.assertTrue(responseId.isPresent());
    }

    @Test
    @DisplayName("This controller method should find a comments by id")
    public void findById(){
        comment1 = new Comment(
            "Good", "good event", UUID.randomUUID(), 0, 0
        );

        commentRepository.save(comment1);

        ResponseEntity<Comment> responseEntity = resTemplate.exchange("/comment/{id}",
                HttpMethod.GET,
                null,
                Comment.class,
                comment1.getId());

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        Comment retrievedComment = responseEntity.getBody();
        Assertions.assertEquals(comment1.getId(), retrievedComment.getId());
        Assertions.assertEquals("Good", retrievedComment.getTitle());
        Assertions.assertEquals("good event", retrievedComment.getComment());
        Assertions.assertEquals(0, retrievedComment.getLikes());
        Assertions.assertEquals(0, retrievedComment.getDislikes());
    }

    @Test
    @DisplayName("This controller method should finnd all comments")
    public void listComments(){
        comment1 = new Comment(
                "Good","good event",UUID.randomUUID(),0,0
        );
        comment2 = new Comment(
                "I love","I love this event",UUID.randomUUID(),5,1
        );

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        ResponseEntity<CustomPageImpl<Comment>> responseEntity = resTemplate.exchange(
                "/comment",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        List<Comment> comments = responseEntity.getBody().getContent();

        assertThat(comments).isNotEmpty();

        assertThat(comments).extracting("title","comment")
                .contains(tuple("Good","good event"),
                tuple("I love","I love this event"));
    }

    @Test
    @DisplayName("This controller method should delete a comment by id")
    public void deleteCommentById(){
        comment1 = new Comment(
                "Good","good event",UUID.randomUUID(), 0, 0
        );

        commentRepository.save(comment1);

        ResponseEntity<Void> responseEntity = resTemplate.exchange(
                "/comment/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                comment1.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var resp = responseEntity.hasBody();
        Assertions.assertFalse(resp);

        Optional<Comment> responseId = this.commentRepository.findById(comment1.getId());
        Assertions.assertFalse(responseId.isPresent());
        Assertions.assertEquals(responseId, Optional.empty());
    }






}