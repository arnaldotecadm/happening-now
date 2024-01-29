package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.context.ActiveProfiles;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ServiceOrganizerTest {

    private ServiceOrganizer serviceOrganizer;

    @Mock
    private OrganizerRepository organizerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        serviceOrganizer = new ServiceOrganizer(organizerRepository);
    }

    @Test
    public void testSave() {
        // Arrange
        Organizer organizer = new Organizer();
        organizer.setId(UUID.randomUUID());
        organizer.setName("Test Organizer");

        when(organizerRepository.save(organizer)).thenReturn(organizer);

        // Act
        Organizer savedOrganizer = serviceOrganizer.save(organizer);

        // Assert
        assertEquals(organizer, savedOrganizer);
        verify(organizerRepository).save(organizer);
    }

    @Test
    public void testFindById() {
        // Arrange
        UUID organizerId = UUID.randomUUID();
        Organizer organizer = new Organizer();
        organizer.setId(organizerId);
        organizer.setName("Test Organizer");

        when(organizerRepository.findById(organizerId)).thenReturn(Optional.of(organizer));

        // Act
        Optional<Organizer> foundOrganizer = serviceOrganizer.findById(organizerId);

        // Assert
        assertEquals(Optional.of(organizer), foundOrganizer);
        verify(organizerRepository).findById(organizerId);
    }
}




//    @Mock
//    Organizer organizer;
//
//    @Mock
//    OrganizerRepository organizerRepository;
//
//    @InjectMocks
//    ServiceOrganizer serviceOrganizer;
//
//    @Test
//    @DisplayName("Save All")
//    void saveAll(){
//        //Arrange
//        Organizer organizer1 = Organizer.bu
//
//        //Act
//
//
//        //Assert
//    }
//
////    @Test
////    @DisplayName("Must save organizer")
////    void save() {
////        //Arrange
////        UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
////        Organizer mockOrganizer = mock(Organizer.class);
////
////        //Act
////        Mockito.when(mockOrganizer.getId()).thenReturn(id);
////        Mockito.when(serviceOrganizer.save(mockOrganizer)).thenReturn(organizer);
////        Mockito.when(organizer.getId()).thenReturn(id);
////
////        var result = serviceOrganizer.save(mockOrganizer);
////
////        // Assert
////        Assertions.assertEquals(result.getId(), mockOrganizer.getId());
////    }
//
//    @Test
//    @DisplayName("Must find by id")
//    void findById() {
//        //Arrange
//        UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
//        Organizer mockOrganizer = mock(Organizer.class);
//
//        //Act
//        Mockito.when(mockOrganizer.getId()).thenReturn(id);
//        Mockito.when(serviceOrganizer.findById(mockOrganizer.getId())).thenReturn(Optional.of(organizer));
//        Mockito.when(organizer.getId()).thenReturn(id);
//
//        var result = organizer.getId();
//
//        //Assert
//        Assertions.assertEquals(result, mockOrganizer.getId());
//    }
//
//    @Test
//
//
//
////    @Test
////    @DisplayName("")
////    void listOrganizer(){
////        Organizer mockOrganizer = mock(Organizer.class);
////
////        Pageable pageable = PageRequest.of(1,50);
////        Page<Organizer> organizers = serviceOrganizer.listOrganizer((PageRequest) pageable);
////
////        Assertions.assertEquals(2, organizers.getContent().size());
////    }
//
////      @Test
////      @DisplayName("Must find all organizer in page")
////      void listOrganizer() {
////        //Arrange
////        //UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
////        Organizer mockOrganizer = mock(Organizer.class);
////
////        when(organizerRepository.findAll(any(PageRequest.class))).thenReturn((Page<Organizer>) organizer);
////        // Act
////        serviceOrganizer.listOrganizer(PageRequest.of(1,50, Sort.Direction.ASC, "name"));
////
////        //Assert
////        Mockito.verify(organizerRepository).findAll(any(PageRequest.class));
////        verifyNoMoreInteractions(organizerRepository);
////    }
//
//    @Test
//    @DisplayName("Must delete organizer")
//    void deleteById(){
//        //Arrange
//        UUID id = UUID.fromString("a0d3b612-cde9-417d-8c47-b268cc295e80");
//        Organizer mockOrganizer = mock(Organizer.class);
//
//        //Act
//        serviceOrganizer.save(organizer);
//        serviceOrganizer.deleteById(organizer.getId());
//        //Assert
//
//    }
//}