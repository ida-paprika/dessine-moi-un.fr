package co.simplon.p25.dessinemoiun.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

//    @Value("${dessinemoiun.api-value.client-name.header}")
//    private String headerName;
//
//    @Value("${dessinemoiun.api-value.credentials.header}")
//    private String headerApiKey;

    public Config() {

    }

    // TODO .properties : ajouter l'url de gandalf
    // FIXME personnaliser avec url et headers
    @Bean
    public RestTemplate restGandalf() {

	return new RestTemplate();
    }
}
