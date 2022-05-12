package co.simplon.p25.dessinemoiun.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.p25.dessinemoiun.dtos.ArtistCard;
import co.simplon.p25.dessinemoiun.repositories.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepo;

    public ArtistServiceImpl(ArtistRepository artistRepo) {
	this.artistRepo = artistRepo;
    }

    @Override
    public List<ArtistCard> getArtistCard() {

	return null;
    }

}
