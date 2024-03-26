package com.happeningnow.service;

import com.happeningnow.model.Comment;
import com.happeningnow.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceComment extends ServiceAbstract<Comment, CommentRepository> {

    public ServiceComment(CommentRepository commentRepository) {
        super(commentRepository);
    }
}