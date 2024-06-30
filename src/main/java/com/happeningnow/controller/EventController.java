package com.happeningnow.controller;


import com.happeningnow.model.Event;
import com.happeningnow.service.ServiceEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/event")
public class EventController extends AbstractController<Event, UUID> {

    public EventController(ServiceEvent service) {
        super(service);
    }
}
