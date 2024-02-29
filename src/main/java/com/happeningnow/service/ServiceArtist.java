package com.happeningnow.service;

import com.happeningnow.model.Artist;
import com.happeningnow.repository.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceArtist {

    private final ArtistRepository artistRepository;

    public ServiceArtist(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public Artist save (Artist artist){
        return this.artistRepository.save(artist);
    }

    public Optional<Artist> findById(UUID uuid){
        return this.artistRepository.findById(uuid);
    }

    public Page<Artist> listArtist(PageRequest pageAble){
        return this.artistRepository.findAll(pageAble);
    }

    public void deleteById(UUID uuid){
        this.artistRepository.deleteById(uuid);
    }
}
