package com.happeningnow.controller;

import com.happeningnow.model.Category;
import com.happeningnow.service.ServiceCategory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController extends AbstractController<Category, UUID>{

    public CategoryController(ServiceCategory service) {
        super(service);
    }
}