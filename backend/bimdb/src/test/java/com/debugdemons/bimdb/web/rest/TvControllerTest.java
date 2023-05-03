package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.TvShowDetails;
import com.debugdemons.bimdb.service.TvService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TvService movieService;

    @Test
    void tvShowDetails() throws Exception {
        TvShowDetails tvShow = new TvShowDetails();
        tvShow.setApiId(305);
        tvShow.setName("Succession");
        when(movieService.getTvShowById(305L)).thenReturn(tvShow);
        this.mockMvc.perform(get("/api/tv/305")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"backdrop_path\":null,\"genre_ids\":null,\"id\":305,\"original_language\":null,\"overview\":null,\"popularity\":0.0,\"poster_path\":null,\"vote_average\":-1.0,\"vote_count\":0,\"first_air_date\":null,\"origin_country\":null,\"name\":\"Succession\",\"original_name\":null,\"episode_run_time\":null,\"genres\":null,\"homepage\":null,\"in_production\":false,\"languages\":null,\"last_air_date\":null,\"networks\":null,\"number_of_episodes\":0,\"number_of_seasons\":0,\"seasons\":null,\"status\":null,\"tagline\":null,\"type\":null}")));
    }
}