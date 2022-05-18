package co.simplon.p25.dessinemoiun.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "art_mediums")
public class ArtMedium extends AbstractEntity {

    @Column(name = "label")
    private String label;

    @Column(name = "minimum_price")
    private Integer minimumPrice;

    @ManyToMany(mappedBy = "artMedium")
    private List<Artist> artist;

    public ArtMedium() {
	//
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(String label) {
	this.label = label;
    }

    public Integer getMinimumPrice() {
	return minimumPrice;
    }

    public void setMinimumPrice(Integer minimumPrice) {
	this.minimumPrice = minimumPrice;
    }

    public List<Artist> getArtist() {
	return artist;
    }

    public void setArtist(List<Artist> artist) {
	this.artist = artist;
    }

    @Override
    public String toString() {
	return String.format("ArtMedium [label=%s, minimumPrice=%s, artist=%s]",
		label, minimumPrice, artist.toString());
    }

}
