package co.simplon.p25.dessinemoiun.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.p25.dessinemoiun.services.ArtworkService;

@RestController
@RequestMapping("galleries")
public class ArtworkController {

    private final ArtworkService service;

    public ArtworkController(ArtworkService service) {
	this.service = service;
    }

    // endpoint to get all galleries for actual user

}
