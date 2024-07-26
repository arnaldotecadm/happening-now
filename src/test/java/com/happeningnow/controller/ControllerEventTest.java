package com.happeningnow.controller;

import com.happeningnow.model.Event;
import com.happeningnow.repository.EventRepository;
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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ControllerEventTest {

    @Autowired
    private TestRestTemplate resTemplate;

    @Autowired
    private EventRepository eventRepository;

    private Event event;

    private Event event2;

    @AfterEach
    public void setUp(){
        this.eventRepository.deleteAll();
    }

    @Test
    @DisplayName("This controller method should save an event")
    void save(){
        LocalDate startDate = LocalDate.of(2024, Month.APRIL, 15);
        LocalDate endDate = LocalDate.of(2024, Month.NOVEMBER, 15);
        Timestamp createdAt = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        Timestamp updatedAt = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        byte[] images = new byte[0];
        boolean status = true;
        boolean payed = true;

        event = new Event("World Cup", "Soccer", "20:00", "22:00", startDate, endDate,updatedAt, createdAt, status, payed,
                "www.github.com", images, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),Collections.emptyList(), Collections.emptyList()
        );

        HttpEntity<Event> saveRequest = new HttpEntity<>(event);
        ResponseEntity<Event> responseEntity = resTemplate.exchange("/event",
                HttpMethod.POST,
                saveRequest,
                Event.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertEquals("World Cup", responseEntity.getBody().getName());
        Assertions.assertEquals("Soccer", responseEntity.getBody().getDescription());
        Assertions.assertEquals(startDate, responseEntity.getBody().getStartDate());
        Assertions.assertEquals(endDate, responseEntity.getBody().getEndDate());
        Assertions.assertEquals(createdAt, responseEntity.getBody().getCreatedAt());
        Assertions.assertEquals(updatedAt, responseEntity.getBody().getUpdatedAt());
        Assertions.assertTrue(responseEntity.getBody().isStatus());
        Assertions.assertTrue(responseEntity.getBody().isPayed());
        Assertions.assertEquals("www.github.com", responseEntity.getBody().getWebPage());

        Optional<Event> responseId = this.eventRepository.findById(responseEntity.getBody().getId());
        Assertions.assertTrue(responseId.isPresent());
    }

    @Test
    @DisplayName("This controller method should find an event by id")
    void findById() {
        LocalDate startDate = LocalDate.of(2024, Month.APRIL, 15);
        LocalDate endDate = LocalDate.of(2024, Month.NOVEMBER, 15);
        Timestamp createdAt = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        Timestamp updatedAt = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        byte[] images = new byte[0];
        boolean status = true;
        boolean payed = true;

        event = new Event("World Cup", "Soccer", "20:00", "22:00", startDate, endDate, createdAt, updatedAt, status, payed,
                "www.github.com", images, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList()
        );

        eventRepository.save(event);
        ResponseEntity<Event> responseEntity = resTemplate.exchange("/event/{id}",
                HttpMethod.GET,
                null,
                Event.class,
                event.getId());

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        Event retrievedEvent = responseEntity.getBody();
        Assertions.assertEquals(event.getId(), retrievedEvent.getId());

        Assertions.assertEquals("World Cup", retrievedEvent.getName());
        Assertions.assertEquals("Soccer", retrievedEvent.getDescription());
        Assertions.assertEquals(startDate, retrievedEvent.getStartDate());
        Assertions.assertEquals(endDate, retrievedEvent.getEndDate());
        Assertions.assertEquals(createdAt, retrievedEvent.getCreatedAt());
        Assertions.assertEquals(updatedAt, retrievedEvent.getUpdatedAt());
        Assertions.assertTrue(retrievedEvent.isStatus());
        Assertions.assertTrue(retrievedEvent.isPayed());
        Assertions.assertEquals("www.github.com", retrievedEvent.getWebPage());

        Optional<Event> responseId = this.eventRepository.findById(responseEntity.getBody().getId());
        Assertions.assertTrue(responseId.isPresent());
    }

    @Test
    @DisplayName("This controller method should find all events")
    void listEvent() {
        LocalDate startDate = LocalDate.of(2024, Month.APRIL, 15);
        LocalDate endDate = LocalDate.of(2024, Month.NOVEMBER, 15);
        Timestamp createdAt = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        Timestamp updatedAt = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        byte[] images = new byte[0];
        boolean status = true;
        boolean payed = true;

        event = new Event("World Cup", "Soccer", "19:00", "00:00", startDate, endDate, createdAt, updatedAt, status, payed,
                "www.github.com/FIFA", images, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList()
        );

        LocalDate startDate2 = LocalDate.of(2024, Month.APRIL, 15);
        LocalDate endDate2 = LocalDate.of(2024, Month.NOVEMBER, 15);
        Timestamp createdAt2 = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        Timestamp updatedAt2 = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        byte[] images2 = new byte[0];
        boolean status2 = false;
        boolean payed2 = false;

        event2 = new Event("volleyball", "volleyball 2", "20:00", "22:00", startDate2, endDate2, createdAt2, updatedAt2, status2, payed2,
                "www.github.com/FIVB", images2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList()
        );

        eventRepository.save(event);
        eventRepository.save(event2);

        ResponseEntity<CustomPageImpl<Event>> responseEntity = resTemplate.exchange(
                "/event",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        List<Event> events = responseEntity.getBody().getContent();

        assertThat(events).isNotEmpty();

        assertThat(events).extracting("name","description", "startTime", "endTime", "webPage")
                .contains(tuple("World Cup","Soccer", "19:00", "00:00", "www.github.com/FIFA"),
                tuple("volleyball", "volleyball 2", "20:00", "22:00","www.github.com/FIVB"));
    }

    @Test
    @DisplayName("This controller method should delete an event by id")
    void deleteEventById() {
        LocalDate startDate = LocalDate.of(2024, Month.APRIL, 15);
        LocalDate endDate = LocalDate.of(2024, Month.NOVEMBER, 15);
        Timestamp createdAt = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        Timestamp updatedAt = new Timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime());
        byte[] images = new byte[0];
        boolean status = true;
        boolean payed = true;

        event = new Event("World Cup", "Soccer", "20:00", "22:00", startDate, endDate, createdAt, updatedAt, status, payed,
                "www.github.com/FIFA", images, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList()
        );

        eventRepository.save(event);

        ResponseEntity<Void> responseEntity = resTemplate.exchange(
                "/event/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                event.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(org.springframework.http.HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var resp = responseEntity.hasBody();
        Assertions.assertFalse(resp);

        Optional<Event> responseId = this.eventRepository.findById(event.getId());
        Assertions.assertFalse(responseId.isPresent());
        Assertions.assertEquals(responseId, Optional.empty());
    }
}