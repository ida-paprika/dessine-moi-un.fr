package co.simplon.p25.dessinemoiun.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProjectCreate {

    @NotBlank
    @Size(min = 20, max = 255)
    private String description;

    @NotBlank
    @Positive
    private Integer price;

    @NotBlank
    @Future
    private LocalDate deadline;

    @NotEmpty
    private Long artMediumId;

    @NotEmpty
    private Long artFormatId;

    @NotEmpty
    private Long artistId;

    public ProjectCreate() {
	//
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Integer getPrice() {
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public LocalDate getDeadline() {
	return deadline;
    }

    public void setDeadline(LocalDate deadline) {
	this.deadline = deadline;
    }

    public Long getArtMediumId() {
	return artMediumId;
    }

    public void setArtMediumId(Long artMediumId) {
	this.artMediumId = artMediumId;
    }

    public Long getArtFormatId() {
	return artFormatId;
    }

    public void setArtFormatId(Long artFormatId) {
	this.artFormatId = artFormatId;
    }

    public Long getArtistId() {
	return artistId;
    }

    public void setArtistId(Long artistId) {
	this.artistId = artistId;
    }

}
