package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ServiceOrganizerTest {

    Organizer organizer;

    @Mock
    OrganizerRepository organizerRepository;

    @InjectMocks
    ServiceOrganizer serviceOrganizer;

    @Test
    void save() {
        var organizerOne = new Organizer();
        organizerOne.setId(UUID.randomUUID());
        organizerOne.setName("Organizer One");
        organizerOne.setAddress("Amial");

        var organizerTwo = new Organizer();
        organizerTwo.setId(UUID.randomUUID());
        organizerTwo.setName("Organizer Two");
        organizerTwo.setAddress("25 de Abril");

        Mockito.when(organizerRepository.save(organizerOne)).thenReturn(organizer);

        var result = organizerRepository.save(organizerOne);

        Assertions.assertEquals(result.getId(), organizerTwo.getId());
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }
}