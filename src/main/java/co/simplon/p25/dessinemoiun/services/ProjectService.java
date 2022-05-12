package co.simplon.p25.dessinemoiun.services;

import javax.validation.Valid;

import co.simplon.p25.dessinemoiun.dtos.ProjectCreate;
import co.simplon.p25.dessinemoiun.dtos.ProjectUpdate;
import co.simplon.p25.dessinemoiun.dtos.ProjectView;

public interface ProjectService {

    void create(@Valid ProjectCreate inputs);

    ProjectView getAllProjects(Long profileId);

    void updateProject(@Valid ProjectUpdate inputs);

    int getEstimatedPrice(Long mediumId, Long formatId);

}
