package com.kibe.AuthProject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AuthProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthProjectApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository repos,
            OAuth2AuthorizedClientService clientService
    ) {
        var manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                repos, clientService
        );
        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        manager.setAuthorizedClientProvider(provider);
        return manager;
    }

    public CommandLineRunner run(
            OAuth2AuthorizedClientManager manager,
            RestTemplate restTemplate,
            @Value("${service2.url}") String service2Url
    ) {
        return args -> {
            var authRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId("keycloak-client")
                    .principal("machine")
                    .build();
            var authorizedClient = manager.authorize(authRequest);
            String token = authorizedClient.getAccessToken().getTokenValue();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            var response =  restTemplate.exchange(
                    service2Url + "/data-sample",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class);
            System.out.println("Response from service 2 " + response);
        };
    }
}
