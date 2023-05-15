package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonServiceTest extends BaseServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    void getPerson() throws JsonProcessingException {
        Person expectedPerson = new Person();
        expectedPerson.setId(123L);
        mockServerExpectGet("https://api.themoviedb.org/3/person/123?append_to_response=combined_credits&language=en", expectedPerson);
        Person actualPerson = personService.getPerson(expectedPerson.getId());
        assertJsonEquals(expectedPerson, actualPerson);
    }
}
