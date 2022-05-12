package co.simplon.p25.dessinemoiun.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.p25.dessinemoiun.dtos.ArtistCreate;
import co.simplon.p25.dessinemoiun.dtos.ProfileCreate;
import co.simplon.p25.dessinemoiun.dtos.ProfileLogin;
import co.simplon.p25.dessinemoiun.security.Jwt;
import co.simplon.p25.dessinemoiun.services.ProfileService;

@RestController
@RequestMapping("profiles")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
	this.service = service;
    }

    // endpoint to get actual user's infos

    @PostMapping("/create-orderer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody ProfileCreate userInput) {
	service.createOrderer(userInput);
    }

    @PostMapping("/create-artist")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody ArtistCreate userInput) {
	service.createArtist(userInput);
    }

    @PostMapping("/login")
    public Jwt loginUser(@RequestBody ProfileLogin userInput) {
	return service.login(userInput);
    }

}
