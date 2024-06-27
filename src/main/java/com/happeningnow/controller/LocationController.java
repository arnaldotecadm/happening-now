package com.happeningnow.controller;

import com.happeningnow.model.Location;
import com.happeningnow.service.ServiceLocation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/location")
public class LocationController extends AbstractController<Location, UUID> {

    public LocationController(ServiceLocation service) {
        super(service);
    }
}