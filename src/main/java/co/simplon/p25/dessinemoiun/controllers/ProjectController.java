package co.simplon.p25.dessinemoiun.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@CrossOrigin(origins = "http://localhost:4200")
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

    // TODO endpoint to create a new project
    @PostMapping
    public void createProject(@Valid @RequestBody ProjectCreate inputs) {
	// get logged user id
	service.create(inputs);
    }

    // TODO endpoint to get all projects of the logged user
    @GetMapping
    public ProjectView getAllProjects() {
	Long profileId = (long) 1; // SecurityHelper.profileId();
	return service.getAllProjects(profileId);
    }

    // TODO endpoint to update the actual project
    @PatchMapping
    public void updateProject(@Valid @RequestBody ProjectUpdate inputs) {
	service.updateProject(inputs);
    }

}
