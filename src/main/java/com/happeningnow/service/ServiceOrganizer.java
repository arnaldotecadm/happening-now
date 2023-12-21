package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class ServiceOrganizer {


    private final OrganizerRepository organizerRepository;

    public Page<Organizer> getOrganizer(){

        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(0, 50, sort);

        return this.organizerRepository.findAll(pageable);
    }
}