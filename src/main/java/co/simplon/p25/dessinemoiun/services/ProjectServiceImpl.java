package co.simplon.p25.dessinemoiun.services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import co.simplon.p25.dessinemoiun.dtos.ProjectCreate;
import co.simplon.p25.dessinemoiun.dtos.ProjectUpdate;
import co.simplon.p25.dessinemoiun.dtos.ProjectView;
import co.simplon.p25.dessinemoiun.entities.ArtFormat;
import co.simplon.p25.dessinemoiun.entities.ArtMedium;
import co.simplon.p25.dessinemoiun.repositories.ArtFormatRepository;
import co.simplon.p25.dessinemoiun.repositories.ArtMediumRepository;
import co.simplon.p25.dessinemoiun.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepo;
    private final ArtMediumRepository mediumRepo;
    private final ArtFormatRepository formatRepo;

    public ProjectServiceImpl(ProjectRepository projectRepo,
	    ArtMediumRepository mediumRepo, ArtFormatRepository formatRepo) {
	this.projectRepo = projectRepo;
	this.mediumRepo = mediumRepo;
	this.formatRepo = formatRepo;
    }

    @Override
    public void create(@Valid ProjectCreate inputs) {
	//
    }

    @Override
    public ProjectView getAllProjects(Long profileId) {
	//
	return null;
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
		+ mediumEntity.getMinimumPrice() * formatEntity.getScale());

	return estimatedPrice;
    }

}
