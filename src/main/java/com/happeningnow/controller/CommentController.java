package com.happeningnow.controller;

import com.happeningnow.model.Comment;
import com.happeningnow.service.ServiceComment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController extends AbstractController<Comment,UUID>{

    public CommentController(ServiceComment service) {
        super(service);
    }
}