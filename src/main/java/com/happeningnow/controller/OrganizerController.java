package com.happeningnow.controller;

import com.happeningnow.model.Organizer;
import com.happeningnow.service.ServiceOrganizer;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizerController {

    private final ServiceOrganizer serviceOrganizer;

    public OrganizerController(ServiceOrganizer serviceOrganizer){
        this.serviceOrganizer = serviceOrganizer;
    }

    @Operation(summary = "Save", description = "Save organizer", method = "POST", tags = "Organizer")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organizer> save(@RequestBody Organizer organizer){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceOrganizer.save(organizer));
    }

    @Operation(summary = "List", description = "List all organizer", method = "GET", tags = "Organizer")
    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Organizer>> list(){
        return ResponseEntity.ok(serviceOrganizer.list(PageRequest.of(0,50, Sort.Direction.ASC, "Name")));
    }



}
