package co.simplon.p25.dessinemoiun.services;

import java.util.List;

import co.simplon.p25.dessinemoiun.dtos.artist.ArtistCard;

public interface ArtistService {

    List<ArtistCard> getArtistCardByTools(Long mediumId, Long formatId);

}
