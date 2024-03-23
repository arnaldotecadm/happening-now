package com.happeningnow.service;

import com.happeningnow.model.Comment;
import com.happeningnow.repository.CommentRepository;
import com.happeningnow.service.factory.ServiceFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceComment extends ServiceFactory<Comment, CommentRepository> {

    private final CommentRepository commentRepository;

    public ServiceComment(CommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
    }
}