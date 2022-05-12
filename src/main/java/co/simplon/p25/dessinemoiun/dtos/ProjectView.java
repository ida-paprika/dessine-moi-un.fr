package co.simplon.p25.dessinemoiun.dtos;

import java.time.LocalDate;

public class ProjectView {

    private Long id;

    private String description;

    private Integer price;

    private LocalDate deadline;

    private String artMediumLabel;

    private String artFormatLabel;

    private String userName;

    private String progressStatus;

    public ProjectView() {
	//
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public String getArtMediumLabel() {
	return artMediumLabel;
    }

    public void setArtMediumLabel(String artMediumLabel) {
	this.artMediumLabel = artMediumLabel;
    }

    public String getArtFormatLabel() {
	return artFormatLabel;
    }

    public void setArtFormatLabel(String artFormatLabel) {
	this.artFormatLabel = artFormatLabel;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getProgressStatus() {
	return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
	this.progressStatus = progressStatus;
    }

}
