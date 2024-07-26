package com.happeningnow.controller;

import com.happeningnow.model.Category;
import com.happeningnow.repository.CategoryRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ControllerCategoryTest {

    @Autowired
    private TestRestTemplate resTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    private Category category2;

    @AfterEach
    public void setUp(){
        this.categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("This controller method should save a category")
    void save() {
        category = new Category(
                "Futebol", "Tag futebol", Collections.emptyList()
        );

        HttpEntity<Category> saveRequest = new HttpEntity<>(category);
        ResponseEntity<Category> responseEntity = resTemplate.exchange("/category",
                HttpMethod.POST,
                saveRequest,
                Category.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertEquals("Futebol", responseEntity.getBody().getName());
        Assertions.assertEquals("Tag futebol", responseEntity.getBody().getDescription());

        Optional<Category> responseId = this.categoryRepository.findById(responseEntity.getBody().getId());
        Assertions.assertTrue(responseId.isPresent());
    }

    @Test
    @DisplayName("This controller method should find a category by id")
    void findById() {
        category = new Category(
                "Futebol", "Tag futebol", Collections.emptyList()
        );

        categoryRepository.save(category);

        ResponseEntity<Category> responseEntity = resTemplate.exchange("/category/{id}",
                HttpMethod.GET,
                null,
                Category.class,
                category.getId());

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        Category retrievedCategory = responseEntity.getBody();
        Assertions.assertEquals(category.getId(), retrievedCategory.getId());
        Assertions.assertEquals("Futebol", retrievedCategory.getName());
        Assertions.assertEquals("Tag futebol", retrievedCategory.getDescription());
    }

    @Test
    @DisplayName("This controller method should find all categories")
    void listCategories() {
        category = new Category(
                "Futebol", "Tag futebol", Collections.emptyList()
        );

        category2 = new Category(
                "Vôlei", "Tag vôlei", Collections.emptyList()
        );

        categoryRepository.save(category);
        categoryRepository.save(category2);

        ResponseEntity<CustomPageImpl<Category>> responseEntity = resTemplate.exchange(
                "/category",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        List<Category> categories = responseEntity.getBody().getContent();

        assertThat(categories).isNotEmpty();

        assertThat(categories).extracting("name", "description")
                .contains(tuple("Futebol", "Tag futebol"),
                        tuple("Vôlei", "Tag vôlei"));
    }

    @Test
    @DisplayName("This controller method should delete a category by id")
    void deleteCategoryById() {
        category = new Category(
                "Futebol", "Tag de futebol", Collections.emptyList()
        );

        categoryRepository.save(category);

        ResponseEntity<Void> responseEntity = resTemplate.exchange(
                "/category/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                category.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var resp = responseEntity.hasBody();
        Assertions.assertFalse(resp);

        Optional<Category> responseId = this.categoryRepository.findById(category.getId());
        Assertions.assertFalse(responseId.isPresent());
        Assertions.assertEquals(responseId,Optional.empty());
    }
}