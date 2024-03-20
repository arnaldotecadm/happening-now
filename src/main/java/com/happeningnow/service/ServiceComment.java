package com.happeningnow.service;

import com.happeningnow.model.Comment;
import com.happeningnow.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceComment {

    private final CommentRepository commentRepository;

    public ServiceComment(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment){
        return this.commentRepository.save(comment);
    }

    public Optional<Comment> findById(UUID uuid){
        return this.commentRepository.findById(uuid);
    }

    public Page<Comment> listComment(PageRequest pageAble){
        return this.commentRepository.findAll(pageAble);
    }

    public void deleteById(UUID uuid){
        this.commentRepository.deleteById(uuid);
    }
}