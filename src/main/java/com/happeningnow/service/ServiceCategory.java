package com.happeningnow.service;

import com.happeningnow.model.Category;
import com.happeningnow.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategory extends ServiceAbstract<Category, CategoryRepository> {

    public ServiceCategory(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }
}
