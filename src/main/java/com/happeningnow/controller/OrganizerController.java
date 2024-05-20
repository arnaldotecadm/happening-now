package com.happeningnow.controller;

import com.happeningnow.dto.OrganizerDTO;
import com.happeningnow.model.Organizer;
import com.happeningnow.repository.OrganizerRepository;
import com.happeningnow.service.ServiceOrganizer;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/happening-now")
public class OrganizerController {

    private final ServiceOrganizer serviceOrganizer;

    public OrganizerController(ServiceOrganizer serviceOrganizer, OrganizerRepository organizerRepository){
        this.serviceOrganizer = serviceOrganizer;
    }

    private OrganizerDTO convertToDto(Organizer organizer) {
        return new OrganizerDTO(organizer.getName(), organizer.getDescription(), organizer.getAddress());
    }

    private Organizer convertToEntity(OrganizerDTO dto) {
        Organizer organizer = new Organizer();
        organizer.setName(dto.getName());
        organizer.setDescription(dto.getDescription());
        organizer.setAddress(dto.getAddress());
        return organizer;
    }

    @PostMapping("/save-organizer")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizerDTO save(@RequestBody OrganizerDTO organizerDTO){
        Organizer organizer = convertToEntity(organizerDTO);
        organizer = serviceOrganizer.save(organizer);
        return convertToDto(organizer);
    }

    @GetMapping("/{id}")
    public OrganizerDTO findById(@PathVariable UUID id){
        Organizer organizer = serviceOrganizer.findById(id)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Organizer not found"));
        return convertToDto(organizer);
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
    public List<OrganizerDTO> list(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Organizer> organizerPage = serviceOrganizer.list(pageRequest);
        return organizerPage.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        serviceOrganizer.findById(id)
                .ifPresentOrElse(organizer -> serviceOrganizer.deleteById(id),
                        () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Organizer not found");
                });
    }
}