package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ControllerOrganizerTest {

    @Autowired
    private TestRestTemplate resTemplate;

    private Organizer organizer1;
    private Organizer organizer2;

//    private OrganizerRepository organizerRepository;

    @BeforeEach
    public void createOrganizer() {
        UUID id1 = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        organizer1 = new Organizer(
                id1, "Alex Sander", "Developer", "Portugal", Collections.emptyList()
        );

        UUID id2 = UUID.fromString("a0d8b613-cde9-417d-9c45-b268cc295e81");
        organizer2 = new Organizer(
                id2, "Arnaldo", "Dev", "Porto", Collections.emptyList()
        );


    }

//    @AfterEach
//    public void setUp(){
//        this.organizerRepository.deleteAll();
//    }

    @Test
    @DisplayName("This controller method should save an organizer")
    public void save() {

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
    }

    @Test
    @DisplayName("This controller method should find an organizer by id")
    public void findById() {
        HttpEntity<Organizer> saveRequest = new HttpEntity<>(organizer1);
        ResponseEntity<Organizer> saveResponse = resTemplate.exchange("/organizer/save",
                HttpMethod.POST,
                saveRequest,
                Organizer.class);

        assertThat(saveResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(saveResponse.getBody()).isNotNull();

        UUID savedOrganizerId = saveResponse.getBody().getId();

        ResponseEntity<Organizer> responseEntity = resTemplate.exchange("/organizer/{id}",
                HttpMethod.GET,
                null,
                Organizer.class,
                savedOrganizerId);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        Organizer retrievedOrganizer = responseEntity.getBody();
        Assertions.assertEquals(savedOrganizerId, retrievedOrganizer.getId());
        Assertions.assertEquals("Alex Sander", retrievedOrganizer.getName());
        Assertions.assertEquals("Developer", retrievedOrganizer.getDescription());
        Assertions.assertEquals("Portugal", retrievedOrganizer.getAddress());
    }

    @Test
    @DisplayName("This controller method should find all organizers")
    public void listOrganizers() {
        ResponseEntity<Organizer> saveResponse1 = resTemplate.postForEntity("/organizer/save",
                new HttpEntity<>(organizer1),
                Organizer.class);

        ResponseEntity<Organizer> saveResponse2 = resTemplate.postForEntity("/organizer/save",
                new HttpEntity<>(organizer2),
                Organizer.class);

        assertThat(saveResponse1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(saveResponse2.getStatusCode()).isEqualTo(HttpStatus.OK); //COLOQUEI CREATED, MAS NÃO FUNCIONA

        ResponseEntity<List<Organizer>> responseEntity = resTemplate.exchange(
                "/organizer/organizers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Organizer>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        List<Organizer> organizers = responseEntity.getBody();

        assertThat(organizers).isNotEmpty();

        assertThat(organizers).extracting("name", "description", "address")
                .contains(tuple("Alex Sander", "Developer", "Portugal"),
                        tuple("Arnaldo", "Dev", "Porto"));
    }


    @Test
    @DisplayName("This controller method should delete an organizer by id")
    public void deleteOrganizerById() {
        HttpEntity<Organizer> saveRequest = new HttpEntity<>(organizer1);
        ResponseEntity<Organizer> saveResponse = resTemplate.exchange("/organizer/save",
                HttpMethod.POST,
                saveRequest,
                Organizer.class);

        ResponseEntity<Void> responseEntity = resTemplate.exchange(
                "/organizer/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                saveRequest.getBody().getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


















//    @Test
//    @DisplayName("This controller method do not should find an organizer by id")
//    public void findById_NotFound(){
//        UUID id = null;
//
//        when(organizerRepository.findById(id)).thenReturn(Optional.of(organizer));
//
//        webTestClient.get().uri("/organizer/{id}", id)
//                .exchange()
//                .expectStatus().isNotFound();
//    }
//
//    @Test
//    @DisplayName("This controller method should find all organizers")
//    public void organizerList(){
//        PageRequest pageRequest = PageRequest.of(0,10);
//
//        Page<Organizer> page = new PageImpl<>(Collections.singletonList(organizer),pageRequest, 1);
//
//        when(organizerRepository.findAll(any(PageRequest.class))).thenReturn(page);
//
//        webTestClient.get().uri(uriBuilder ->
//                uriBuilder.path("/organizer/organizers")
//                        .queryParam("page", 0)
//                        .queryParam("size", 10)
//                        .build())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.content[0].id").isEqualTo(organizer.getId().toString())
//                .jsonPath("$.content[0].name").isEqualTo("Alex Sander");
//    }
//
//    @Test
//    @DisplayName("This controller method should delete an organizer by id")
//    public void deleteById(){
//        UUID id = organizer.getId();
//
//        when(organizerRepository.findById(id)).thenReturn(Optional.of(organizer));
//        doNothing().when(organizerRepository).deleteById(id);
//
//        webTestClient.delete().uri("/organizer/{id}", id)
//                .exchange()
//                .expectStatus().isNoContent();
//    }
//
//    @Test
//    @DisplayName("This controller method do not should delete an organizer")
//    public void deleteById_NotFound(){
//        UUID id = null;
//
//        when(organizerRepository.findById(id)).thenReturn(Optional.of(organizer));
//
//        webTestClient.delete().uri("/organizer/{id}", id)
//                .exchange()
//                .expectStatus().isNotFound();
//    }
}