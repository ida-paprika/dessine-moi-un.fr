package co.simplon.p25.dessinemoiun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.p25.dessinemoiun.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
