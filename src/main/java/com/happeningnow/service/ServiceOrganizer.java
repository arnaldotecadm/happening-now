package com.happeningnow.service;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import com.happeningnow.service.factory.ServiceFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrganizer extends ServiceFactory<Organizer, OrganizerRepository> {

    private final OrganizerRepository organizerRepository;

    public ServiceOrganizer(OrganizerRepository organizerRepository){
        super(organizerRepository);
        this.organizerRepository = organizerRepository;
    }
}