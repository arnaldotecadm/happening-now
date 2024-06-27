package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
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
class ServiceOrganizerTest {

    @Autowired
    private ServiceOrganizer serviceOrganizer;

    @Autowired
    private OrganizerRepository organizerRepository;

    private Organizer organizer;

    @BeforeEach
    public void createOrganizer(){
        UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        organizer = new Organizer(
                "Alex Sander","Developer","Portugal", Collections.emptyList()
        );
    }

    @AfterEach
    public void setUp(){
        this.organizerRepository.deleteAll();
    }

    @Test
    @DisplayName("Must save a new organizer")
    public void save(){

        var result = serviceOrganizer.save(organizer);

        var size = organizerRepository.findAll().size();

        Assertions.assertEquals(result.getName(), organizer.getName());
        Assertions.assertEquals(1, size, "The quantity of organizer is different");
    }

    @Test
    @DisplayName("Must find organizer by ID")
    public void findById(){

        serviceOrganizer.save(organizer);

        var result = serviceOrganizer.findById(organizer.getId());

        Assertions.assertEquals(result.getId(), organizer.getId());
    }

    @Test
    @DisplayName("Must find all organizers")
    public void listOrganizer(){

        serviceOrganizer.save(organizer);

        Page<Organizer>listOfOrganizer =
                    serviceOrganizer.list(PageRequest.of(0,50, Sort.Direction.ASC, "name"));

        Assertions.assertFalse(listOfOrganizer.getContent().isEmpty());
        Assertions.assertTrue(listOfOrganizer.stream().anyMatch(l -> l.getName().equals(organizer.getName())));
    }

    @Test
    @DisplayName("Must delete organizer")
    public void deleteById(){

        serviceOrganizer.save(organizer);

        Page<Organizer>listOfOrganizer =
                serviceOrganizer.list(PageRequest.of(0,50, Sort.Direction.ASC,"name"));

        Assertions.assertFalse(listOfOrganizer.getContent().isEmpty());

        serviceOrganizer.deleteById(organizer.getId());

        listOfOrganizer =
                serviceOrganizer.list(PageRequest.of(0,50, Sort.Direction.ASC,"name"));

        Assertions.assertTrue(listOfOrganizer.getContent().isEmpty());
    }
}