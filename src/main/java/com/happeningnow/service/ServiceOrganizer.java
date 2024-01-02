package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor

@Service
public class ServiceOrganizer {

    @Autowired
    private final OrganizerRepository organizerRepository;

    @Transactional
    public Organizer save(Organizer organizer){
        return this.organizerRepository.save(organizer);
    }

    public Optional<Organizer> findById(UUID uuid){
        return this.organizerRepository.findById(uuid);
    }

    public Page<Organizer> findAll(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 1, size = 50) Pageable pageable){
     return organizerRepository.findAll(pageable);
    }

    public void delete(UUID uuid){
        this.organizerRepository.deleteById(uuid);
    }
}