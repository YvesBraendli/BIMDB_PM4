package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonService extends BaseService {

    protected PersonService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        super(movieDBApiConfig, restTemplate);
    }

    public Person getPerson(Long id) {
        String url = movieDBApiConfig.getBaseUrl() + "person/" + id + "?append_to_response=combined_credits";
        return restTemplate.getForObject(url, Person.class);
    }
}
