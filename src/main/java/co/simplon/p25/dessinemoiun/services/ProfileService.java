package co.simplon.p25.dessinemoiun.services;

import javax.validation.Valid;

import co.simplon.p25.dessinemoiun.dtos.ArtistCreate;
import co.simplon.p25.dessinemoiun.dtos.ProfileCreate;
import co.simplon.p25.dessinemoiun.dtos.ProfileLogin;
import co.simplon.p25.dessinemoiun.security.Jwt;

public interface ProfileService {

    Jwt login(ProfileLogin userInput);

    void createArtist(@Valid ArtistCreate userInput);

    void createOrderer(@Valid ProfileCreate userInput);

}
