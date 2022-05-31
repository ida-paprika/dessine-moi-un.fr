package co.simplon.p25.dessinemoiun.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.p25.dessinemoiun.entities.ArtFormat;
import co.simplon.p25.dessinemoiun.entities.ArtMedium;
import co.simplon.p25.dessinemoiun.entities.Artist;
import co.simplon.p25.dessinemoiun.entities.Profile;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findAllByArtMediumAndArtFormatAndAvailable(
	    ArtMedium mediumEntity, ArtFormat formatEntity, boolean b);

//    @Query(value = "SELECT a.id FROM artists a WHERE a.profile_id = :profileId", nativeQuery = true)
//    Artist findOneByProfileId(@Param("profileId") Long profileId);

    Artist findOneByProfile(Profile profile);

    Optional<Artist> findByArtistNameIgnoreCase(String artistName);

}
