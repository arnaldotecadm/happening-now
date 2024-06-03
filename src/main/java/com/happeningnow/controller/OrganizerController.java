package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.service.ServiceOrganizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    private final ServiceOrganizer serviceOrganizer;

    public OrganizerController(ServiceOrganizer serviceOrganizer){
        this.serviceOrganizer = serviceOrganizer;
    }

    @PostMapping("/save")
    public ResponseEntity<Organizer> save(@RequestBody Organizer organizer){
        Organizer saveOrganizer = serviceOrganizer.save(organizer);
        return new ResponseEntity<>(saveOrganizer,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Organizer>> findById(@PathVariable UUID id) {
        Optional<Organizer> organizer = serviceOrganizer.findById(id);
        return ResponseEntity.ok(organizer);
    }

    @GetMapping("/organizers")
    public ResponseEntity<Page<Organizer>> findAll(Pageable pageAble){
        PageRequest pageRequest = PageRequest.of(pageAble.getPageNumber(),
                pageAble.getPageSize());
        Page<Organizer> organizerList = serviceOrganizer.list(pageRequest);
        return new ResponseEntity<>(organizerList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        serviceOrganizer.deleteById(id);
    }
}