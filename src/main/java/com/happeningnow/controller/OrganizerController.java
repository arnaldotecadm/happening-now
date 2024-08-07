package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.service.ServiceOrganizer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/organizer")
public class OrganizerController extends AbstractController<Organizer, UUID>{

    public OrganizerController(ServiceOrganizer service){
        super(service);
    }
}