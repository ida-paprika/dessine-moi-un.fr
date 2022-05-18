package co.simplon.p25.dessinemoiun.services;

import java.util.List;

import javax.validation.Valid;

import co.simplon.p25.dessinemoiun.dtos.ProjectCreate;
import co.simplon.p25.dessinemoiun.dtos.ProjectUpdate;
import co.simplon.p25.dessinemoiun.dtos.ProjectView;

public interface ProjectService {

    void create(@Valid ProjectCreate inputs);

    void updateProject(@Valid ProjectUpdate inputs);

    int getEstimatedPrice(Long mediumId, Long formatId);

    List<ProjectView> getArtistProjects(Long profileId);

    List<ProjectView> getOrdererProjects(Long profileId);

    void deleteProject(Long projectId);

}
