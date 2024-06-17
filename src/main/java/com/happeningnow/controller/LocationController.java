package com.happeningnow.controller;

import com.happeningnow.model.Location;
import com.happeningnow.service.ServiceLocation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController extends AbstractController<Location, ServiceLocation> {

    public LocationController(ServiceLocation service) {
        this.service = service;
    }
}