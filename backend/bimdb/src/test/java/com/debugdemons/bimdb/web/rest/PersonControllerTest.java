package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.CombinedCredits;
import com.debugdemons.bimdb.domain.Person;
import com.debugdemons.bimdb.service.MovieService;
import com.debugdemons.bimdb.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void getPerson() throws Exception {
        Person person = new Person();
        person.setId(132L);
        person.setBirthday(new Date(0));
        person.setDeathday(null);
        person.setName("John");
        person.setPopularity(1);
        person.setBiography("Lorem Ipsum");
        person.setPlaceOfBirth("Zürich");
        person.setProfilePath("John.png");
        person.setCombinedCredits(new CombinedCredits());

        when(personService.getPerson(123L)).thenReturn(person);
        this.mockMvc.perform(get("/api/person/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":132,\"birthday\":\"1970-01-01T00:00:00.000+00:00\",\"deathday\":null,\"name\":\"John\",\"popularity\":1,\"biography\":\"Lorem Ipsum\",\"placeOfBirth\":\"ZÃ¼rich\",\"profilePath\":\"John.png\",\"combinedCredits\":{\"cast\":null,\"crew\":null}}")));
    }
}
