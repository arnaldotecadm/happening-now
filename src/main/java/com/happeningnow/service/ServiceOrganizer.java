package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceOrganizer {

    private final OrganizerRepository organizerRepository;

    public ServiceOrganizer(OrganizerRepository organizerRepository){
        this.organizerRepository = organizerRepository;
    }

    public Organizer save(Organizer organizer){
        return this.organizerRepository.save(organizer);
    }

    public Optional<Organizer> findById(UUID uuid){
        return this.organizerRepository.findById(uuid);
    }

    public Page<Organizer> listOrganizer(PageRequest pageable){
        return organizerRepository.findAll(pageable);
    }

    public void deleteById(UUID uuid){
        this.organizerRepository.deleteById(uuid);
    }
}