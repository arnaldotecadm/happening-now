package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import com.happeningnow.service.ServiceOrganizer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = OrganizerController.class)
public class ControllerOrganizerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private OrganizerRepository organizerRepository;

    @MockBean
    private ServiceOrganizer serviceOrganizer;

    private Organizer organizer;

    @BeforeEach
    public void createOrganizer(){
        UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        organizer = new Organizer(
                id,"Alex Sander","Developer","Portugal", Collections.emptyList()
        );
    }

    @AfterEach
    public void setUp(){
        this.organizerRepository.deleteAll();
    }

    @Test
    @DisplayName("This controller method should save an organizer")
    public void save(){

        when(serviceOrganizer.save(any(Organizer.class))).thenReturn(organizer);

        webTestClient.post().uri("/organizer/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(organizer), Organizer.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(organizer.getId().toString())
                .jsonPath("$.name").isEqualTo("Alex Sander")
                .jsonPath("$.description").isEqualTo("Developer");
    }

    @Test
    @DisplayName("This controller method should find an organizer by id")
    public void findById(){

        UUID id = organizer.getId();

        when(serviceOrganizer.findById(id)).thenReturn(Optional.of(organizer));

        webTestClient.get().uri("/organizer/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(id.toString())
                .jsonPath("$.name").isEqualTo("Alex Sander");
    }

    @Test
    @DisplayName("This controller method do not should find an organizer by id")
    public void findById_NotFound(){
        UUID id = null;

        when(serviceOrganizer.findById(id)).thenReturn(Optional.of(organizer));

        webTestClient.get().uri("/organizer/{id}", id)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("This controller method should find all organizers")
    public void organizerList(){
        PageRequest pageRequest = PageRequest.of(0,10);

        Page<Organizer> page = new PageImpl<>(Collections.singletonList(organizer),pageRequest, 1);

        when(serviceOrganizer.list(any(PageRequest.class))).thenReturn(page);

        webTestClient.get().uri(uriBuilder ->
                uriBuilder.path("/organizer/organizers")
                        .queryParam("page", 0)
                        .queryParam("size", 10)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.content[0].id").isEqualTo(organizer.getId().toString())
                .jsonPath("$.content[0].name").isEqualTo("Alex Sander");
    }

    @Test
    @DisplayName("This controller method should delete an organizer by id")
    public void deleteById(){
        UUID id = organizer.getId();

        when(serviceOrganizer.findById(id)).thenReturn(Optional.of(organizer));
        doNothing().when(organizerRepository).deleteById(id);

        webTestClient.delete().uri("/organizer/{id}", id)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @DisplayName("This controller method do not should delete an organizer")
    public void deleteById_NotFound(){
        UUID id = null;

        when(serviceOrganizer.findById(id)).thenReturn(Optional.of(organizer));

        webTestClient.delete().uri("/organizer/{id}", id)
                .exchange()
                .expectStatus().isNotFound();
    }
}