package com.happeningnow.controller;

import com.happeningnow.model.Artist;
import com.happeningnow.repository.ArtistRepository;
import com.happeningnow.util.CustomPageImpl;
import org.junit.jupiter.api.*;
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
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ControllerArtistTest {

    @Autowired
    private TestRestTemplate resTemplate;

    @Autowired
    private ArtistRepository artistRepository;

    private Artist artist;

    private Artist artist2;

    @AfterEach
    public void setUp(){
        this.artistRepository.deleteAll();
    }

    @Test
    @DisplayName("This controller method should save an artist")
    public void save(){
        artist = new Artist(
                "Alex Sander","software engineer", Collections.emptyList()
        );

        HttpEntity<Artist> saveRequest = new HttpEntity<>(artist);
        ResponseEntity<Artist> responseEntity = resTemplate.exchange("/artist",
                HttpMethod.POST,
                saveRequest,
                Artist.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertEquals("Alex Sander", responseEntity.getBody().getName());
        Assertions.assertEquals("software engineer", responseEntity.getBody().getDescription());

        Optional<Artist> responseId = this.artistRepository.findById(responseEntity.getBody().getId());
        Assertions.assertTrue(responseId.isPresent());
    }

    @Test
    @DisplayName("This controller method should find an artist by id")
    public void findById() {
        artist = new Artist(
          "Alex Sander","software engineer", Collections.emptyList()
        );

        artistRepository.save(artist);

        ResponseEntity<Artist> responseEntity = resTemplate.exchange("/artist/{id}",
                HttpMethod.GET,
                null,
                Artist.class,
                artist.getId());

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();

        Artist retrievedArtist = responseEntity.getBody();
        Assertions.assertEquals(artist.getId(), retrievedArtist.getId());
        Assertions.assertEquals("Alex Sander", retrievedArtist.getName());
        Assertions.assertEquals("software engineer", responseEntity.getBody().getDescription());
    }

    @Test
    @DisplayName("This controller method should find all artist")
    public void listArtist() {
        artist = new Artist(
                "Alex Sander","software engineer", Collections.emptyList()
        );

        artist2 = new Artist(
                "Arnaldo","software engineer", Collections.emptyList()
        );

        artistRepository.save(artist);
        artistRepository.save(artist2);

        ResponseEntity<CustomPageImpl<Artist>> responseEntity = resTemplate.exchange("/artist",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        List<Artist> artists = responseEntity.getBody().getContent();

        assertThat(artists).isNotEmpty();

        assertThat(artists).extracting("name","description")
                .contains(tuple("Alex Sander","software engineer"),
                        tuple("Arnaldo","software engineer"));
    }

    @Test
    @DisplayName("This controller method should delete an artist by id")
    public void deleteArtistById(){
        artist = new Artist(
                "Alex Sander", "software engineer", Collections.emptyList()
        );

        artistRepository.save(artist);

        ResponseEntity<Void> responseEntity =resTemplate.exchange(
                "/artist/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                artist.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        var resp = responseEntity.hasBody();
        Assertions.assertFalse(resp);

        Optional<Artist> responseId = this.artistRepository.findById(artist.getId());
        Assertions.assertFalse(responseId.isPresent());
        Assertions.assertEquals(responseId, Optional.empty());
    }
}