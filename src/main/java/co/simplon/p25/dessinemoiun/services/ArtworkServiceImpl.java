package co.simplon.p25.dessinemoiun.services;

import org.springframework.stereotype.Service;

import co.simplon.p25.dessinemoiun.repositories.ArtworkRepository;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private final ArtworkRepository artworkRepo;

    public ArtworkServiceImpl(ArtworkRepository artworkRepo) {
	this.artworkRepo = artworkRepo;
    }

}
