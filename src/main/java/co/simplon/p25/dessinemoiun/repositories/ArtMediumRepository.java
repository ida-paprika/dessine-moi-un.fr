package co.simplon.p25.dessinemoiun.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.p25.dessinemoiun.entities.ArtMedium;

public interface ArtMediumRepository extends JpaRepository<ArtMedium, Long> {

    List<ArtMedium> findAllByOrderByLabelAsc();

}
