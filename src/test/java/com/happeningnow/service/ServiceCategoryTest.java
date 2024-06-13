package com.happeningnow.service;

import com.happeningnow.model.Category;
import com.happeningnow.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class ServiceCategoryTest {

    @Autowired
    private ServiceCategory serviceCategory;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void createCategory(){
        UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        category = new Category(id, "Tecnologia", "Technology category ", Collections.emptyList());
    }

    @AfterEach
    public void setUp(){
        this.categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Must save category")
    public void save(){
        var result = serviceCategory.save(category);

        var size = categoryRepository.findAll().size();

        Assertions.assertEquals(result.getName(), category.getName());
        Assertions.assertEquals(1, size,  "The quantity of category is different");
    }

    @Test
    @DisplayName("Must find category by id")
    public void findById(){
        serviceCategory.save(category);

        var result = serviceCategory.findById(category.getId());

        Assertions.assertEquals(result.getId(), category.getId());
    }

    @Test
    @DisplayName("Must find all category")
    public void categoryList(){
        serviceCategory.save(category);

        Page<Category>listOfCategory =
                serviceCategory.list(PageRequest.of(0,50, Sort.Direction.ASC,"Name"));

        Assertions.assertFalse(listOfCategory.getContent().isEmpty());
        Assertions.assertTrue(listOfCategory.stream().anyMatch(l -> l.getName().equals(category.getName())));
    }

    @Test
    @DisplayName("Mus delete category")
    public void deleteById(){
        serviceCategory.save(category);

        Page<Category>listOfCategory =
                serviceCategory.list(PageRequest.of(0,50, Sort.Direction.ASC, "Name"));

        Assertions.assertFalse(listOfCategory.getContent().isEmpty());

        serviceCategory.deleteById(category.getId());

        listOfCategory =
                serviceCategory.list(PageRequest.of(0,50, Sort.Direction.ASC, "Name"));

        Assertions.assertTrue(listOfCategory.getContent().isEmpty());
    }
}
