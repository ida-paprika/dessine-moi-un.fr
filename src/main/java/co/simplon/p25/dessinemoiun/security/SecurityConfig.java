package co.simplon.p25.dessinemoiun.security;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${dessinemoiun.security.jwt.secret}")
    private String secret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.cors().and().csrf().disable().logout().disable()
		//
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// ANONYMOUS authorizations
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/profiles/create-orderer",
			"/profiles/create-artist", "/profiles/login")
		.permitAll().and().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/artmediums", "/artformats",
			"/projects/price")
		.permitAll()
//		.and().authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/artworks/create").permitAll()
//		// ARTIST authorizations ("/artists/**" =ok= "/artists" ?)
//		.and().authorizeRequests().antMatchers("/artists/**")
//		.hasRole("ARTIST")
//		// ORDERER authorizations
//		.and().authorizeRequests().antMatchers("").hasRole("ORDERER")
		// LOGGED authorizations
		.and().authorizeRequests().anyRequest().authenticated().and()
		.oauth2ResourceServer().jwt();

    }

    @Bean
    public JwtDecoder jwtDecoder() {
	SecretKey secretKey = new SecretKeySpec(secret.getBytes(),
		"HMACSHA256");
	return NimbusJwtDecoder.withSecretKey(secretKey)
		.macAlgorithm(MacAlgorithm.HS256).build();
    }
}
