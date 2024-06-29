package com.happeningnow.service;

import com.happeningnow.model.Event;
import com.happeningnow.repository.EventRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.lang.reflect.Array;
import java.time.*;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


@SpringBootTest
@ActiveProfiles("test")
public class ServiceEventTest {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ServiceEvent serviceEvent;

    private Event event;

    @BeforeEach
    public void creatEventPast(){
        LocalDate startDate = LocalDate.of(2024, Month.MARCH, 18);
        LocalDate endDate = LocalDate.of(2024, Month.MARCH, 20);
        byte[] images = new byte[0];
        boolean status = false;
        boolean payed = false;

        event = new Event("Birthday", "Birthday the Alex Sander", startDate, endDate,"Alex Sander", "Alex Sander", status, payed,
                "www.github.com", images, Collections.emptyList(), Collections.emptyList(),Collections.emptyList(),Collections.emptyList(), Collections.emptyList()
                );

    }

    @AfterEach
    public void setUp(){
        this.eventRepository.deleteAll();
    }

    @Test
    @DisplayName("Must save a new Event")
    public void save(){
        var result = serviceEvent.save(event);

        var size = eventRepository.findAll().size();

        Assertions.assertEquals(result.getName(), event.getName());
        Assertions.assertEquals(1, size, "The quantity of event is different");

        //Assertions.assertTrue(event.isStatus(), "Event not be false");
        Assertions.assertFalse(event.isStatus(), "Event not be a true");

        //Assertions.assertTrue(event.isPayed(), "Unpaid event ");
        Assertions.assertFalse(event.isPayed(),"Paid event");
    }

    @Test
    @DisplayName("Must find event by id")
    public void findById(){
        serviceEvent.save(event);

        var result = eventRepository.findById(event.getId());

        Assertions.assertEquals(result.get().getId(), event.getId());
    }

    @Test
    @DisplayName("Must find all events")
    public void eventList(){
        serviceEvent.save(event);

        Page<Event>listOfEvents =
            serviceEvent.list(PageRequest.of(0,50, Sort.Direction.ASC,"Name"));

        Assertions.assertFalse(listOfEvents.getContent().isEmpty());
        Assertions.assertTrue(listOfEvents.stream().anyMatch(l -> l.getName().equals(event.getName())));
    }

    @Test
    @DisplayName("Must delete an event")
    public void deleteById(){
        serviceEvent.save(event);

        Page<Event>listOfEvent =
            serviceEvent.list(PageRequest.of(0,50, Sort.Direction.ASC,"Name"));

        Assertions.assertFalse(listOfEvent.getContent().isEmpty());

        serviceEvent.deleteById(event.getId());

        listOfEvent =
            serviceEvent.list(PageRequest.of(0,50, Sort.Direction.ASC, "Name"));

        Assertions.assertTrue(listOfEvent.getContent().isEmpty());
    }
}
