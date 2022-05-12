package co.simplon.p25.dessinemoiun.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.p25.dessinemoiun.dtos.ArtistCard;
import co.simplon.p25.dessinemoiun.dtos.ArtistPublic;
import co.simplon.p25.dessinemoiun.dtos.ArtistTop3;
import co.simplon.p25.dessinemoiun.services.ArtistService;

@RestController
@RequestMapping("artists")
public class ArtistController {

    private final ArtistService service;

    public ArtistController(ArtistService service) {
	this.service = service;
    }

    // endpoint to get all actual artist's infos

    // endpoint to get ArtistCard
    @GetMapping("/cards")
    public List<ArtistCard> getArtistCard() {
	return service.getArtistCard();
    }

    // endpoint to artist's public profile
    public ArtistPublic getArtistPublic() {

	return null;
    }

    // endpoint to get the 3 "artists of the month"
    public List<ArtistTop3> getArtistsOfTheMonth() {

	return null;
    }

}
