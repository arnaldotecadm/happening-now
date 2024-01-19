package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("test")
class ServiceOrganizerTest {

    @Mock
    Organizer organizer;

    @Mock
    OrganizerRepository organizerRepository;

    @InjectMocks
    ServiceOrganizer serviceOrganizer;

    @Test
    @DisplayName("Must save organizer")
    void save() {
        //Arrange
        UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
        Organizer mockOrganizer = mock(Organizer.class);

        //Act
        Mockito.when(mockOrganizer.getId()).thenReturn(id);
        Mockito.when(organizerRepository.save(mockOrganizer)).thenReturn(organizer);
        Mockito.when(organizer.getId()).thenReturn(id);

        var result = serviceOrganizer.save(mockOrganizer);

        // Assert
        Assertions.assertEquals(result.getId(), mockOrganizer.getId());
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