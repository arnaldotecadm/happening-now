package com.happeningnow.service;

import com.happeningnow.model.Location;
import com.happeningnow.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceLocation extends ServiceAbstract<Location, LocationRepository>{

    public ServiceLocation(LocationRepository locationRepository) {
        super(locationRepository);
    }
}
