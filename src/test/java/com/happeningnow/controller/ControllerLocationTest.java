package com.happeningnow.controller;

import com.happeningnow.model.Location;
import com.happeningnow.repository.LocationRepository;
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
public class ControllerLocationTest {

    @Autowired
    private TestRestTemplate resTemplate;

    @Autowired
    private LocationRepository locationRepository;

    private Location location1;

    private Location location2;

    @AfterEach
    public void setUp(){
        this.locationRepository.deleteAll();
    }

    @Test
    @DisplayName("This controller method should save an location")
    public void save() {
        location1 = new Location(
                "Sander", "Dev", "Brasil", Collections.emptyList()
        );

        HttpEntity<Location> saveRequest = new HttpEntity<>(location1);
        ResponseEntity<Location> responseEntity = resTemplate.exchange("/location",
                HttpMethod.POST,
                saveRequest,
                Location.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertEquals("Sander", responseEntity.getBody().getName());
        Assertions.assertEquals("Dev", responseEntity.getBody().getDescription());
        Assertions.assertEquals("Brasil", responseEntity.getBody().getAddress());

        Optional<Location> responseId = this.locationRepository.findById(responseEntity.getBody().getId());
        Assertions.assertTrue(responseId.isPresent());
    }

    @Test
    @DisplayName("This controller method should find an location by id")
    public void findById() {
        location1 = new Location(
                "Sander", "Dev", "Portugal", Collections.emptyList()
        );

        locationRepository.save(location1);

        ResponseEntity<Location> responseEntity = resTemplate.exchange("/location/{id}",
                HttpMethod.GET,
                null,
                Location.class,
                location1.getId());

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        Location retrievedOrganizer = responseEntity.getBody();
        Assertions.assertEquals(location1.getId(), retrievedOrganizer.getId());
        Assertions.assertEquals("Sander", retrievedOrganizer.getName());
        Assertions.assertEquals("Dev", retrievedOrganizer.getDescription());
        Assertions.assertEquals("Portugal", retrievedOrganizer.getAddress());
    }

    @Test
    @DisplayName("This controller method should find all locations")
    public void listLocations() {
        location1 = new Location(
                "Sander", "Developer", "Portugal", Collections.emptyList()
        );

        location2 = new Location(
                "Arnaldo", "Dev", "Porto", Collections.emptyList()
        );

        locationRepository.save(location1);
        locationRepository.save(location2);

        ResponseEntity<CustomPageImpl<Location>> responseEntity = resTemplate.exchange(
                "/location",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        List<Location> locations = responseEntity.getBody().getContent();

        assertThat(locations).isNotEmpty();

        assertThat(locations).extracting("name", "description", "address")
                .contains(tuple("Sander", "Developer", "Portugal"),
                        tuple("Arnaldo", "Dev", "Porto"));
    }

    @Test
    @DisplayName("This controller method should delete an organizer by id")
    public void deleteLocationById() {
        location1 = new Location(
                "Alex Sander", "Developer", "Portugal", Collections.emptyList()
        );

        locationRepository.save(location1);

        ResponseEntity<Void> responseEntity = resTemplate.exchange(
                "/location/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                location1.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var resp = responseEntity.hasBody();
        Assertions.assertFalse(resp);

        Optional<Location> responseId = this.locationRepository.findById(location1.getId());
        Assertions.assertFalse(responseId.isPresent());
        Assertions.assertEquals(responseId,Optional.empty());
    }
}