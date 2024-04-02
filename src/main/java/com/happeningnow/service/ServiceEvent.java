package com.happeningnow.service;

import com.happeningnow.model.Event;
import com.happeningnow.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceEvent extends ServiceAbstract<Event, EventRepository>{
    public ServiceEvent(EventRepository eventRepository) {
        super(eventRepository);
    }
}
