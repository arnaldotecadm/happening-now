package com.happeningnow.service;

import com.happeningnow.model.Artist;
import com.happeningnow.repository.ArtistRepository;
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
public class ServiceArtistTest {

    @Autowired
    private ServiceArtist serviceArtist;

    @Autowired
    private ArtistRepository artistRepository;

    private Artist artist;

    @BeforeEach
    public void createArtist(){
        artist = new Artist(
                "Alex Sander","Developer", Collections.emptyList()
        );
    }

    @AfterEach
    public void setUp(){
        this.artistRepository.deleteAll();
    }

    @Test
    @DisplayName("Must save a new artist")
    public void save(){
        var result = serviceArtist.save(artist);

        var size = artistRepository.findAll().size();

        Assertions.assertEquals(result.getName(), artist.getName());
        Assertions.assertEquals(1, size, "The quantity of artist is different");
    }

    @Test
    @DisplayName("Must find artist by id")
    public void findById(){
        serviceArtist.save(artist);

        var result = serviceArtist.findById(artist.getId());

        Assertions.assertEquals(result.getId(), artist.getId());
    }

    @Test
    @DisplayName("Must find all artist")
    public void artistList(){
        serviceArtist.save(artist);

        Page<Artist>listOfArtist =
                serviceArtist.list(PageRequest.of(0, 50, Sort.Direction.ASC, "Name"));

        Assertions.assertFalse(listOfArtist.getContent().isEmpty());
        Assertions.assertTrue(listOfArtist.stream().anyMatch(l -> l.getName().equals(artist.getName())));
    }

    @Test
    @DisplayName("Must delete artist")
    public void deleteById(){
        serviceArtist.save(artist);

        Page<Artist>listOfArtist =
                serviceArtist.list(PageRequest.of(0,50, Sort.Direction.ASC, "name"));

        Assertions.assertFalse(listOfArtist.getContent().isEmpty());

        serviceArtist.deleteById(artist.getId());

        listOfArtist =
                serviceArtist.list(PageRequest.of(0,50, Sort.Direction.ASC, "name"));

        Assertions.assertTrue(listOfArtist.getContent().isEmpty());
    }
}