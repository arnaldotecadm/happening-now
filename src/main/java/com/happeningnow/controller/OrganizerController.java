package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import com.happeningnow.service.ServiceOrganizer;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public Organizer findById(@PathVariable UUID id){
        return serviceOrganizer.findById(id)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Organizer not found"));
    }

//    @GetMapping("/organizers")
//    public Page<Organizer> list(@ModelAttribute Organizer filter,
//                                @RequestParam(defaultValue = "0") int page,
//                                @RequestParam(defaultValue = "10") int size){
//        PageRequest pageRequest = PageRequest.of(page, size);
//        ExampleMatcher exampleMatcher = ExampleMatcher
//                .matching()
//                .withIgnoreCase()
//                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
//        Example<Organizer> example = Example.of(filter, exampleMatcher);
//        return serviceOrganizer.list(pageRequest);
//    }

    @GetMapping("/organizers")
    public List<Organizer> list(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return serviceOrganizer.list(pageRequest).getContent();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        serviceOrganizer.findById(id)
                .map(organizer -> {
                    serviceOrganizer.deleteById(id);
                    return organizer;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Organizer not found"));
    }
}