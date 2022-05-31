package co.simplon.p25.dessinemoiun.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.p25.dessinemoiun.dtos.project.ProjectCreate;
import co.simplon.p25.dessinemoiun.dtos.project.ProjectUpdate;
import co.simplon.p25.dessinemoiun.dtos.project.ProjectView;
import co.simplon.p25.dessinemoiun.entities.ArtFormat;
import co.simplon.p25.dessinemoiun.entities.ArtMedium;
import co.simplon.p25.dessinemoiun.entities.Artist;
import co.simplon.p25.dessinemoiun.entities.Profile;
import co.simplon.p25.dessinemoiun.entities.Project;
import co.simplon.p25.dessinemoiun.repositories.ArtFormatRepository;
import co.simplon.p25.dessinemoiun.repositories.ArtMediumRepository;
import co.simplon.p25.dessinemoiun.repositories.ArtistRepository;
import co.simplon.p25.dessinemoiun.repositories.ProfileRepository;
import co.simplon.p25.dessinemoiun.repositories.ProgressStatusRepository;
import co.simplon.p25.dessinemoiun.repositories.ProjectRepository;
import co.simplon.p25.dessinemoiun.security.SecurityHelper;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepo;
    private final ArtMediumRepository mediumRepo;
    private final ArtFormatRepository formatRepo;
    private final ProfileRepository profileRepo;
    private final ArtistRepository artistRepo;
    private final ProgressStatusRepository progressRepo;

    public ProjectServiceImpl(ProjectRepository projectRepo,
	    ArtMediumRepository mediumRepo, ArtFormatRepository formatRepo,
	    ArtistRepository artistRepo, ProfileRepository profileRepo,
	    ProgressStatusRepository progressRepo) {
	this.projectRepo = projectRepo;
	this.mediumRepo = mediumRepo;
	this.formatRepo = formatRepo;
	this.profileRepo = profileRepo;
	this.artistRepo = artistRepo;
	this.progressRepo = progressRepo;
    }

    @Transactional
    @Override
    public void create(@Valid ProjectCreate inputs) {
	Project entity = new Project();

	entity.setDescription(inputs.getDescription());
	entity.setDeadline(inputs.getDeadline());
	entity.setPrice(inputs.getPrice());
	entity.setArtMedium(mediumRepo.findById(inputs.getArtMediumId()).get());
	entity.setArtFormat(formatRepo.findById(inputs.getArtFormatId()).get());
	entity.setArtist(artistRepo.findById(inputs.getArtistId()).get());
	entity.setProfile(this.getActualUserProfile());
	entity.setCreatedAt(LocalDateTime.now());
	entity.setProgressStatus(progressRepo.findById((long) 1).get());

	projectRepo.save(entity);
    }

    @Override
    public void updateProject(@Valid ProjectUpdate inputs) {
	// TODO Auto-generated method stub

    }

    @Override
    public int getEstimatedPrice(Long mediumId, Long formatId) {

	ArtMedium mediumEntity = mediumRepo.findById(mediumId).get();
	ArtFormat formatEntity = formatRepo.findById(formatId).get();

	Integer estimatedPrice = (int) (formatEntity.getMinimumPrice()
		+ mediumEntity.getMinimumPrice()
			* formatEntity.getMultiplier());

	return estimatedPrice;
    }

    @Override
    public List<ProjectView> getArtistProjects() {

	Artist artist = artistRepo
		.findOneByProfile(this.getActualUserProfile());

	List<Project> entityList = projectRepo.findAllByArtist(artist);
	List<ProjectView> projectList = new ArrayList<ProjectView>();
	for (Project p : entityList) {
	    ProjectView item = new ProjectView(p.getId(),
		    p.getCreatedAt().toLocalDate(), p.getDescription(),
		    p.getPrice(), p.getDeadline(), p.getArtMedium().getLabel(),
		    p.getArtFormat().getLabel(), p.getProfile().getEmail(),
		    p.getProgressStatus().getStatus());
	    projectList.add(item);
	}
	return projectList;
    }

    @Override
    public List<ProjectView> getOrdererProjects() {
	Profile profile = this.getActualUserProfile();

	List<Project> entityList = projectRepo.findAllByProfile(profile);
	List<ProjectView> projectList = new ArrayList<ProjectView>();
	for (Project p : entityList) {
	    ProjectView item = new ProjectView(p.getId(),
		    p.getCreatedAt().toLocalDate(), p.getDescription(),
		    p.getPrice(), p.getDeadline(), p.getArtMedium().getLabel(),
		    p.getArtFormat().getLabel(), p.getArtist().getArtistName(),
		    p.getProgressStatus().getStatus());
	    projectList.add(item);
	}
	return projectList;
    }

    @Transactional
    @Override
    public void deleteProject(Long projectId) {
	String profileUuid = SecurityHelper.authenticatedProfileUuid();
	System.out.println(profileUuid);
	projectRepo.deleteById(projectId);
    }

    private String getProfileName(Profile entity) {
	return String.format("%s %s.", entity.getFirstName(),
		entity.getLastName().charAt(0));
    }

    private Profile getActualUserProfile() {
	UUID uuid = UUID.fromString(SecurityHelper.authenticatedProfileUuid());
	Profile profile = profileRepo.findOneByUuid(uuid);
	return profile;
    }

}
