package co.simplon.p25.dessinemoiun.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.simplon.p25.dessinemoiun.dtos.ArtistCard;
import co.simplon.p25.dessinemoiun.entities.ArtFormat;
import co.simplon.p25.dessinemoiun.entities.ArtMedium;
import co.simplon.p25.dessinemoiun.entities.Artist;
import co.simplon.p25.dessinemoiun.repositories.ArtFormatRepository;
import co.simplon.p25.dessinemoiun.repositories.ArtMediumRepository;
import co.simplon.p25.dessinemoiun.repositories.ArtistRepository;
import co.simplon.p25.dessinemoiun.repositories.ArtworkRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepo;
    private final ArtMediumRepository mediumRepo;
    private final ArtFormatRepository formatRepo;
    private final ArtworkRepository artworkRepo;

    public ArtistServiceImpl(ArtistRepository artistRepo,
	    ArtMediumRepository mediumRepo, ArtFormatRepository formatRepo,
	    ArtworkRepository artworkRepo) {
	this.artistRepo = artistRepo;
	this.mediumRepo = mediumRepo;
	this.formatRepo = formatRepo;
	this.artworkRepo = artworkRepo;
    }

    @Transactional
    @Override
    public List<ArtistCard> getArtistCardByTools(Long mediumId, Long formatId) {

	ArtMedium mediumEntity = mediumRepo.findById(mediumId).get();
	ArtFormat formatEntity = formatRepo.findById(formatId).get();

	List<Artist> entityList = artistRepo
		.findAllByArtMediumAndArtFormatAndAvailable(mediumEntity,
			formatEntity, true);

	List<ArtistCard> artistList = new ArrayList<ArtistCard>();

	for (Artist a : entityList) {
	    ArtistCard artist = new ArtistCard();

	    artist.setId(a.getId());
	    artist.setArtistName(a.getArtistName());

	    String cover = artworkRepo.findOneByArtistAndCover(a, true)
		    .getFileName();
	    artist.setArtwork(cover);

	    artistList.add(artist);
	}

	return artistList;
    }

}
