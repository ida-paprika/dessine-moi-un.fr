package co.simplon.p25.dessinemoiun.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.p25.dessinemoiun.dtos.ArtMediumView;
import co.simplon.p25.dessinemoiun.entities.ArtMedium;
import co.simplon.p25.dessinemoiun.repositories.ArtMediumRepository;

@Service
public class ArtMediumServiceImpl implements ArtMediumService {

    private final ArtMediumRepository mediumRepo;

    public ArtMediumServiceImpl(ArtMediumRepository mediumRepo) {
	this.mediumRepo = mediumRepo;
    }

    @Override
    public List<ArtMediumView> getAllMediums() {
	List<ArtMedium> entityList = mediumRepo.findAllByOrderByLabelAsc();
	List<ArtMediumView> dtoList = new ArrayList<ArtMediumView>();

	for (ArtMedium am : entityList) {
	    ArtMediumView artMedium = new ArtMediumView();
	    artMedium.setId(am.getId());
	    artMedium.setLabel(am.getLabel());
	    dtoList.add(artMedium);
	}

	return dtoList;
    }

}
