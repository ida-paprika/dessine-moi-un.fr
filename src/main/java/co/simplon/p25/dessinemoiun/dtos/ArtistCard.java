package co.simplon.p25.dessinemoiun.dtos;

public class ArtistCard {

    private Long id;

    private String artistName;

    private ArtworkView artwork;

    public ArtistCard() {
	//
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getArtistName() {
	return artistName;
    }

    public void setArtistName(String artistName) {
	this.artistName = artistName;
    }

    public ArtworkView getArtwork() {
	return artwork;
    }

    public void setArtwork(ArtworkView artwork) {
	this.artwork = artwork;
    }

}
