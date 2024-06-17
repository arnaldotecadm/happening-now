package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.service.ServiceOrganizer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizer")
public class OrganizerController extends AbstractController<Organizer, ServiceOrganizer>{

    public OrganizerController(ServiceOrganizer service){
        this.service = service;
    }
}