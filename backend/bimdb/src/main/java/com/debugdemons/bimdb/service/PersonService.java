package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersonService extends BaseService {

    protected PersonService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        super(movieDBApiConfig, restTemplate);
    }

    public Person getPerson(Long id) {
        TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl())
                .withEndpoint("person/" + id)
                .withAppendToResponse(List.of("combined_credits"));
        return restTemplate.getForObject(tmdbUrlBuilder.build(), Person.class);
    }
}
