package co.simplon.p25.dessinemoiun.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "art_formats")
public class ArtFormat extends AbstractEntity {

    @Column(name = "label")
    private String label;

    @Column(name = "minimum_price")
    private Integer minimumPrice;

    @Column(name = "scale")
    private double scale;

    @ManyToMany(mappedBy = "artFormat")
    private List<Artist> artist;

    public ArtFormat() {
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

    public double getScale() {
	return scale;
    }

    public void setScale(double scale) {
	this.scale = scale;
    }

    public List<Artist> getArtist() {
	return artist;
    }

    public void setArtist(List<Artist> artist) {
	this.artist = artist;
    }

}
