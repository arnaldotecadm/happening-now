package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@AllArgsConstructor

@Service
public class ServiceOrganizer {

    //@Autowired
    private final OrganizerRepository organizerRepository;

    @Transactional
    public Organizer create(Organizer organizer){
        organizer.setId(null);
        organizer = this.organizerRepository.save(organizer);
        return organizer;
    }

    public Page<Organizer> findById(UUID uuid){

        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(0, 50, sort);

        return this.organizerRepository.findAll(pageable);
    }

    @Transactional
    public Organizer update(Organizer organizer){
        Organizer newOrganizer = findById(organizer.getId());
        newOrganizer.setName(organizer.getName());
        return this.organizerRepository.save(newOrganizer);
    }

    public void delete(UUID uuid){
        findById(uuid);
        try {
            this.organizerRepository.deleteById(uuid);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}