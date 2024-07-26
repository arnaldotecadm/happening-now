package com.happeningnow.controller;

import com.happeningnow.model.Artist;
import com.happeningnow.service.ServiceArtist;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/artist")
public class ArtistController extends AbstractController<Artist, UUID>{

    public ArtistController(ServiceArtist service) {
        super(service);
    }
}
