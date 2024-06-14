package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import com.happeningnow.util.CustomPageImpl;
import org.junit.jupiter.api.*;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ControllerOrganizerTest {

    @Autowired
    private TestRestTemplate resTemplate;

    @Autowired
    private OrganizerRepository organizerRepository;

    private Organizer organizer1;

    private Organizer organizer2;

    @AfterEach
    public void setUp(){
        this.organizerRepository.deleteAll();
    }

    @Test
    @DisplayName("This controller method should save an organizer")
    public void save() {
        UUID id1 = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        organizer1 = new Organizer(
                id1, "Alex Sander", "Developer", "Portugal", Collections.emptyList()
        );

        HttpEntity<Organizer> saveRequest = new HttpEntity<>(organizer1);
        ResponseEntity<Organizer> responseEntity = resTemplate.exchange("/organizer/save",
                HttpMethod.POST,
                saveRequest,
                Organizer.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertEquals("Alex Sander", responseEntity.getBody().getName());
        Assertions.assertEquals("Developer", responseEntity.getBody().getDescription());
        Assertions.assertEquals("Portugal", responseEntity.getBody().getAddress());

        Optional<Organizer> responseId = this.organizerRepository.findById(responseEntity.getBody().getId());
        Assertions.assertTrue(responseId.isPresent());
    }

    @Test
    @DisplayName("This controller method should find an organizer by id")
    public void findById() {
        UUID id1 = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        organizer1 = new Organizer(
                id1, "Alex Sander", "Developer", "Portugal", Collections.emptyList()
        );

        organizerRepository.save(organizer1);

        ResponseEntity<Organizer> responseEntity = resTemplate.exchange("/organizer/{id}",
                HttpMethod.GET,
                null,
                Organizer.class,
                organizer1.getId());

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        Organizer retrievedOrganizer = responseEntity.getBody();
        Assertions.assertEquals(organizer1.getId(), retrievedOrganizer.getId());
        Assertions.assertEquals("Alex Sander", retrievedOrganizer.getName());
        Assertions.assertEquals("Developer", retrievedOrganizer.getDescription());
        Assertions.assertEquals("Portugal", retrievedOrganizer.getAddress());
    }

    @Test
    @DisplayName("This controller method should find all organizers")
    public void listOrganizers() {
        UUID id1 = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        organizer1 = new Organizer(
                id1, "Alex Sander", "Developer", "Portugal", Collections.emptyList()
        );

        UUID id2 = UUID.fromString("a0d8b613-cde9-417d-9c45-b268cc295e81");
        organizer2 = new Organizer(
                id2, "Arnaldo", "Dev", "Porto", Collections.emptyList()
        );

        organizerRepository.save(organizer1);
        organizerRepository.save(organizer2);

        ResponseEntity<CustomPageImpl<Organizer>> responseEntity = resTemplate.exchange(
                "/organizer/organizers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        List<Organizer> organizers = responseEntity.getBody().getContent();

        assertThat(organizers).isNotEmpty();

        assertThat(organizers).extracting("name", "description", "address")
                .contains(tuple("Alex Sander", "Developer", "Portugal"),
                        tuple("Arnaldo", "Dev", "Porto"));
}


    @Test
    @DisplayName("This controller method should delete an organizer by id")
    public void deleteOrganizerById() {
        UUID id1 = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        organizer1 = new Organizer(
                id1, "Alex Sander", "Developer", "Portugal", Collections.emptyList()
        );

        organizerRepository.save(organizer1);

        ResponseEntity<Void> responseEntity = resTemplate.exchange(
                "/organizer/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                organizer1.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var resp = responseEntity.hasBody();
        Assertions.assertFalse(resp);

        Optional<Organizer> responseId = this.organizerRepository.findById(id1);
        Assertions.assertFalse(responseId.isPresent());
        Assertions.assertEquals(responseId,Optional.empty());
    }
}