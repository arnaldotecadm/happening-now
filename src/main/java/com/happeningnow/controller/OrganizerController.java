package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.service.ServiceOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrganizerController {

    private final ServiceOrganizer serviceOrganizer;

    @Autowired
    public OrganizerController(ServiceOrganizer serviceOrganizer){
        this.serviceOrganizer = serviceOrganizer;
    }



    @PostMapping(value = "/organizer/save",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Organizer save(@RequestBody Organizer organizer){
        System.out.println(organizer.getId());
        System.out.println(organizer.getName());
        System.out.println(organizer.getDescription());
        System.out.println(organizer.getAddress());
        System.out.println(organizer.getEventList());

        return organizer;
    }



}
