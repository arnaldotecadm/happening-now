package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Organizer> listOrganizer(@PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 1, size = 50) PageRequest pageable){ //tirei Pageable e coloquei PageRequest
        return organizerRepository.findAll(pageable);
    }

    public void deleteById(UUID uuid){ // ask / alteration void to Organizer // optional name altered delete to deleteById
        this.organizerRepository.deleteById(uuid);
    }
}