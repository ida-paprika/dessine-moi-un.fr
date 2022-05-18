package co.simplon.p25.dessinemoiun.services;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.simplon.p25.dessinemoiun.dtos.ArtistCreate;
import co.simplon.p25.dessinemoiun.dtos.ProfileCreate;
import co.simplon.p25.dessinemoiun.dtos.ProfileLogin;
import co.simplon.p25.dessinemoiun.entities.Artist;
import co.simplon.p25.dessinemoiun.entities.Profile;
import co.simplon.p25.dessinemoiun.repositories.ArtistRepository;
import co.simplon.p25.dessinemoiun.repositories.ProfileRepository;
import co.simplon.p25.dessinemoiun.security.GandalfJwt;
import co.simplon.p25.dessinemoiun.security.Jwt;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Value("gandalf.api.root-uri")
    private String gandalfUrl;

    @Value("${dessinemoiun.api-value.client-name.header}")
    private String headerName;

    @Value("${dessinemoiun.api-value.credentials.header}")
    private String headerApiKey;

    private final ProfileRepository profileRepo;

    private final ArtistRepository artistRepo;

    private final RestTemplate gandalf;

    public ProfileServiceImpl(ProfileRepository profileRepo,
	    RestTemplate gandalfRestTemplate, ArtistRepository artistRepo) {
	this.profileRepo = profileRepo;
	this.artistRepo = artistRepo;
	this.gandalf = gandalfRestTemplate;
    }

    @Override
    public void createOrderer(@Valid ProfileCreate userInputs) {
	userInputs.setRole("ROLE_ORDERER");

//	gandalf.postForLocation("users/create", userInput);

//	HttpHeaders headers = setHeaders();
//	HttpEntity<ProfileCreate> entity = new HttpEntity<ProfileCreate>(
//		userInput, headers);
//
//	String resourceUrl = "http://localhost:9090/users/create";
//
//	ResponseEntity<UUID> response = gandalf.postForEntity(resourceUrl,
//		entity, UUID.class);

	ResponseEntity<UUID> response = gandalf.postForEntity("/users/create",
		userInputs, UUID.class);

	UUID uuid = response.getBody();

	Profile newProfile = new Profile(uuid, userInputs.getEmail());
	profileRepo.save(newProfile);
    }

    @Override
    public void createArtist(@Valid ArtistCreate userInputs) {
	ProfileCreate profile = new ProfileCreate(userInputs.getEmail(),
		userInputs.getPassword());
	profile.setRole("ROLE_ARTIST");

	HttpHeaders headers = setHeaders();
	HttpEntity<ProfileCreate> entity = new HttpEntity<ProfileCreate>(
		profile, headers);

	String resourceUrl = "http://localhost:9090/users/create";

	ResponseEntity<UUID> response = gandalf.postForEntity(resourceUrl,
		entity, UUID.class);

	UUID uuid = response.getBody();

	Profile newProfile = new Profile(uuid, userInputs.getEmail());
	profileRepo.save(newProfile);

	Artist newArtist = new Artist(userInputs.getArtistName(), false,
		newProfile);
	artistRepo.save(newArtist);

    }

    @Override
    public Jwt login(ProfileLogin userInputs) throws BadCredentialsException {

	Profile profile = profileRepo
		.findByEmailIgnoreCase(userInputs.getEmail()).orElseThrow(
			() -> new BadCredentialsException("Profile not found"));

//	HttpHeaders headers = setHeaders();
//
//	HttpEntity<ProfileLogin> entity = new HttpEntity<ProfileLogin>(
//		userInput, headers);
//
//	String resourceUrl = "http://localhost:9090/users/login";
//
//	ResponseEntity<GandalfJwt> response = gandalf.postForEntity(resourceUrl,
//		entity, GandalfJwt.class);

	ResponseEntity<GandalfJwt> response = gandalf
		.postForEntity("/users/login", userInputs, GandalfJwt.class);

	GandalfJwt jwt = response.getBody();

//	UUID uuid = jwt.getUuid();

	Jwt token = new Jwt(jwt.getToken());

	return token;

    }

    private HttpHeaders setHeaders() {
	HttpHeaders headers = new HttpHeaders();
	headers.set("client-name", headerName);
	headers.set("client-api-key", headerApiKey);
	return headers;
    }

}
