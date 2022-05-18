package co.simplon.p25.dessinemoiun.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.p25.dessinemoiun.dtos.ProjectCreate;
import co.simplon.p25.dessinemoiun.dtos.ProjectUpdate;
import co.simplon.p25.dessinemoiun.dtos.ProjectView;
import co.simplon.p25.dessinemoiun.services.ProjectService;

@RestController
@RequestMapping("projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
	this.service = service;
    }

    @GetMapping("/price")
    public int getEstimatedPrice(@RequestParam(name = "mediumId") Long mediumId,
	    @RequestParam(name = "formatId") Long formatId) {
	return service.getEstimatedPrice(mediumId, formatId);
    }

    @PostMapping
    public void createProject(@Valid @RequestBody ProjectCreate inputs) {
	// TODO get logged user id
//	String uuid = SecurityHelper.authenticatedProfileUuid();
//	System.out.println("UUID : " + uuid);
	service.create(inputs);
    }

    @GetMapping("/artist")
    public List<ProjectView> getArtistProjects(
	    @RequestParam(name = "profileId") Long profileId) {
	return service.getArtistProjects(profileId);
    }

    @GetMapping("/orderer")
    public List<ProjectView> getOrdererProjects(
	    @RequestParam(name = "profileId") Long profileId) {
	return service.getOrdererProjects(profileId);
    }

    @PatchMapping
    public void updateProject(@Valid @RequestBody ProjectUpdate inputs) {
	service.updateProject(inputs);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long projectId) {
	service.deleteProject(projectId);
    }

}
