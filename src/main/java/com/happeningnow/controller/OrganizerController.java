package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import com.happeningnow.service.ServiceOrganizer;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/happening-now")
public class OrganizerController {

    private ServiceOrganizer serviceOrganizer;

    public OrganizerController(ServiceOrganizer serviceOrganizer, OrganizerRepository organizerRepository){
        this.serviceOrganizer = serviceOrganizer;
    }

    @PostMapping("/save-organizer")
    @ResponseStatus(HttpStatus.CREATED)
    public Organizer save(@RequestBody Organizer organizer){
        return serviceOrganizer.save(organizer);
    }

//    @GetMapping
//    public Organizer findById(@PathVariable)

    @GetMapping("/organizers")
    public Page<Organizer> list(PageRequest pageRequest){
        return serviceOrganizer.list(pageRequest);
    }



}
