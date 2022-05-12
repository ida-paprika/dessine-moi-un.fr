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

    @Value("gandalf.api.access.url")
    private String gandalfUrl;

    @Value("${dessinemoiun.api-value.client-name.header}")
    private String headerName;

    @Value("${dessinemoiun.api-value.credentials.header}")
    private String headerApiKey;

    private final ProfileRepository profileRepo;

    private final ArtistRepository artistRepo;

    private final RestTemplate restTemplate;

    public ProfileServiceImpl(ProfileRepository profileRepo,
	    RestTemplate restTemplate, ArtistRepository artistRepo) {
	this.profileRepo = profileRepo;
	this.artistRepo = artistRepo;
	this.restTemplate = restTemplate;
    }

    @Override
    public void createOrderer(@Valid ProfileCreate userInput) {
	userInput.setRole("ROLE_ORDERER");

	HttpHeaders headers = setHeaders();
	HttpEntity<ProfileCreate> entity = new HttpEntity<ProfileCreate>(
		userInput, headers);

	String resourceUrl = "http://localhost:9090/users/create";

	ResponseEntity<UUID> response = restTemplate.postForEntity(resourceUrl,
		entity, UUID.class);

	UUID uuid = response.getBody();

	Profile newProfile = new Profile(uuid, userInput.getEmail());
	profileRepo.save(newProfile);
    }

    @Override
    public void createArtist(@Valid ArtistCreate userInput) {
	ProfileCreate profile = new ProfileCreate(userInput.getEmail(),
		userInput.getPassword(), "ROLE_ARTIST");

	HttpHeaders headers = setHeaders();
	HttpEntity<ProfileCreate> entity = new HttpEntity<ProfileCreate>(
		profile, headers);

	String resourceUrl = "http://localhost:9090/users/create";

	ResponseEntity<UUID> response = restTemplate.postForEntity(resourceUrl,
		entity, UUID.class);

	UUID uuid = response.getBody();

	Profile newProfile = new Profile(uuid, userInput.getEmail());
	profileRepo.save(newProfile);

	Artist newArtist = new Artist(userInput.getArtistName(), false);
	artistRepo.save(newArtist);

    }

    @Override
    public Jwt login(ProfileLogin userInput) throws BadCredentialsException {

	Profile profile = profileRepo
		.findByEmailIgnoreCase(userInput.getEmail()).orElseThrow(
			() -> new BadCredentialsException("Profile not found"));

	HttpHeaders headers = setHeaders();

	HttpEntity<ProfileLogin> entity = new HttpEntity<ProfileLogin>(
		userInput, headers);

	String resourceUrl = "http://localhost:9090/users/login";

	ResponseEntity<GandalfJwt> response = restTemplate
		.postForEntity(resourceUrl, entity, GandalfJwt.class);

	GandalfJwt jwt = response.getBody();

	UUID uuid = jwt.getUuid();
	System.out.println(jwt.getUuid());

	Jwt token = new Jwt(jwt.getToken());

	return token;

	// récupérer UUID pour faire le lien avec Profile

    }

    private HttpHeaders setHeaders() {
	HttpHeaders headers = new HttpHeaders();
	headers.set("client-name", headerName);
	headers.set("client-api-key", headerApiKey);
	return headers;
    }

    // TODO create method "newUser()"

}
