package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrganizer extends ServiceAbstract<Organizer, OrganizerRepository> {

    public ServiceOrganizer(OrganizerRepository organizerRepository){
        super(organizerRepository);
    }
}