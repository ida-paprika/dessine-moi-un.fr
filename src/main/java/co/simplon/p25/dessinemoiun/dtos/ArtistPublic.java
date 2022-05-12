package co.simplon.p25.dessinemoiun.dtos;

import java.util.List;

public class ArtistPublic {

    private Long id;

    private String artistName;

    private String instagramUrl;

    private List<ArtMediumView> artMediums;

    private List<ArtFormatView> artFormats;

    private List<ArtworkView> artworks;

    public ArtistPublic() {
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

    public String getInstagramUrl() {
	return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
	this.instagramUrl = instagramUrl;
    }

    public List<ArtMediumView> getArtMediums() {
	return artMediums;
    }

    public void setArtMediums(List<ArtMediumView> artMediums) {
	this.artMediums = artMediums;
    }

    public List<ArtFormatView> getArtFormats() {
	return artFormats;
    }

    public void setArtFormats(List<ArtFormatView> artFormats) {
	this.artFormats = artFormats;
    }

    public List<ArtworkView> getArtworks() {
	return artworks;
    }

    public void setArtworks(List<ArtworkView> artworks) {
	this.artworks = artworks;
    }

}
