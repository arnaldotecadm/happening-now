package com.happeningnow.service;

import com.happeningnow.model.Artist;
import com.happeningnow.repository.ArtistRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceArtist extends ServiceAbstract<Artist, ArtistRepository> {

    public ServiceArtist(ArtistRepository artistRepository) {
        super(artistRepository);
    }
}