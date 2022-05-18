package co.simplon.p25.dessinemoiun.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.simplon.p25.dessinemoiun.exceptions.RestTemplateErrorHandler;

@Configuration
public class RestTemplateConfig {

    @Value("${gandalf.api.root-uri}")
    private String gandalfUri;

    @Value("${dessinemoiun.api-value.client-name.header}")
    private String headerName;

    @Value("${dessinemoiun.api-value.credentials.header}")
    private String headerApiKey;

    private final ObjectMapper objectMapper;

    // Default ObjectMapper (Jackson) injected by Spring
    public RestTemplateConfig(ObjectMapper objectMapper) {
	this.objectMapper = objectMapper;
    }

    // Default RestTemplateBuilder injected by Spring
    @Bean
    public RestTemplate gandalfRestTemplate(RestTemplateBuilder builder) {
	RestTemplate template = builder.rootUri(gandalfUri)
		.defaultHeader("client-name", headerName)
		.defaultHeader("client-api-key", headerApiKey)
		.errorHandler(errorHandler()).build();
	template.setRequestFactory(requestFactory());
	return template;
    }

    @Bean
    protected ResponseErrorHandler errorHandler() {
	return new RestTemplateErrorHandler(objectMapper);
    }

    @Bean
    protected ClientHttpRequestFactory requestFactory() {
	return new HttpComponentsClientHttpRequestFactory();
    }

}
