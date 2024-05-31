package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ControllerOrganizerTest {

    @Autowired
    private TestRestTemplate resTemplate;

    private Organizer organizer;

//    @BeforeEach
//    public void createOrganizer(){
//        UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
//        organizer = new Organizer(
//                id,"Alex Sander","Developer","Portugal", Collections.emptyList()
//        );
//    }

//    @AfterEach
//    public void setUp(){
//        this.organizerRepository.deleteAll();
//    }

    @Test
    @DisplayName("This controller method should save an organizer")
    public void save(){

        HttpEntity<Organizer> saveRequest = new HttpEntity<>(organizer);
        ResponseEntity<Organizer> responseEntity = resTemplate.exchange("/organizer/save",
                HttpMethod.POST,
                saveRequest,
                Organizer.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertEquals("Developer", responseEntity.getBody().getDescription());
        Assertions.assertEquals("Alex Sander", responseEntity.getBody().getName());
        Assertions.assertEquals("Portugal", responseEntity.getBody().getAddress());
    }

    @Test
    @DisplayName("This controller method should find an organizer by id")
    public void findById(){
        HttpEntity<Organizer> saveRequest = new HttpEntity<>(organizer);
        ResponseEntity<Organizer> saveResponse = resTemplate.exchange("/organizer/save",
                HttpMethod.POST,
                saveRequest,
                Organizer.class);

        assertThat(saveResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(saveResponse.getBody()).isNotNull();

        UUID savedOrganizerId = saveResponse.getBody().getId();

        ResponseEntity<Organizer> responseEntity = resTemplate.exchange("/organizers/{id}",
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
//
//        UUID id = organizer.getId();
//
//        when(organizerRepository.findById(id)).thenReturn(Optional.of(organizer));
//
//        webTestClient.get().uri("/organizer/{id}", id)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.id").isEqualTo(id.toString())
//                .jsonPath("$.name").isEqualTo("Alex Sander");
//    }
//
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