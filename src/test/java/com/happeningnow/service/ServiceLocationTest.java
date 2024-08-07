package com.happeningnow.service;

import com.happeningnow.model.Location;
import com.happeningnow.repository.LocationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

@SpringBootTest
@ActiveProfiles("test")
class ServiceLocationTest {

    @Autowired
    private ServiceLocation serviceLocation;

    @Autowired
    private LocationRepository locationRepository;

    private Location location;

    @BeforeEach
    public void creatLocation(){
        location = new Location("Event", "Technology event", "Portugal", Collections.emptyList());
    }

    @AfterEach
    public void setUp(){
        this.locationRepository.deleteAll();
    }

    @Test
    @DisplayName("Must save location")
    void save(){
        var result = serviceLocation.save(location);

        var size = locationRepository.findAll().size();

        Assertions.assertEquals(result.getName(), location.getName());
        Assertions.assertEquals(1, size,"Quantity Location test");

        Assertions.assertFalse(location.getDescription().isEmpty(), "Description location test");

        Assertions.assertFalse(location.getAddress().isEmpty(),"Adress location test");
    }

    @Test
    @DisplayName("Must find locations by id")
    void findById(){
        serviceLocation.save(location);

        var result = serviceLocation.findById(location.getId());

        Assertions.assertEquals(result.getId(), location.getId());
    }

    @Test
    @DisplayName("Must find all locations")
    void locationList(){
        serviceLocation.save(location);

        Page<Location>listOfLocations =
                serviceLocation.list(PageRequest.of(0,50, Sort.Direction.ASC, "Name"));
        Assertions.assertFalse(listOfLocations.getContent().isEmpty());

        Assertions.assertTrue(listOfLocations.stream().anyMatch(l -> l.getName().equals(location.getName())));
    }

    @Test
    @DisplayName("Must delete locations")
    void deleteById(){
        serviceLocation.save(location);
        Page<Location>listOfLocations =
                serviceLocation.list(PageRequest.of(0,50, Sort.Direction.ASC,"Name"));
        Assertions.assertFalse(listOfLocations.getContent().isEmpty());

        serviceLocation.deleteById(location.getId());

        listOfLocations =
                serviceLocation.list(PageRequest.of(0,50, Sort.Direction.ASC, "Name"));
        Assertions.assertTrue(listOfLocations.getContent().isEmpty());
    }
}