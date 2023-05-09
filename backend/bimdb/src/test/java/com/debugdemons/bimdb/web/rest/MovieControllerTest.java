package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.MovieDetails;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import com.debugdemons.bimdb.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void movieDetails() throws Exception {
        MovieDetails movie = new MovieDetails();
        movie.setApiId(538);
        movie.setName("Interstellar");
        when(movieService.getMovieById(538L)).thenReturn(movie);
        this.mockMvc.perform(get("/api/movie/538")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"backdrop_path\":null,\"genre_ids\":null,\"id\":538,\"original_language\":null,\"overview\":null,\"popularity\":0.0,\"poster_path\":null,\"vote_average\":-1.0,\"vote_count\":0,\"adult\":false,\"original_title\":\"Interstellar\",\"release_date\":null,\"title\":null,\"video\":false,\"budget\":0,\"genres\":null,\"homepage\":null,\"imdb_id\":null,\"revenue\":0,\"runtime\":null,\"status\":null,\"tagline\":null,\"credits\":null,\"recommendations\":null,\"similar\":null}")));
    }

    @Test
    void watchProviders() throws Exception {
        WatchProvidersResult watchProvidersResult = new WatchProvidersResult();
        watchProvidersResult.setWatchProviders(new ArrayList<>());
        when(movieService.getWatchProviders(538L)).thenReturn(movie);
        this.mockMvc.perform(get("/api/movie/538")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"backdrop_path\":null,\"genre_ids\":null,\"id\":538,\"original_language\":null,\"overview\":null,\"popularity\":0.0,\"poster_path\":null,\"vote_average\":-1.0,\"vote_count\":0,\"adult\":false,\"original_title\":\"Interstellar\",\"release_date\":null,\"title\":null,\"video\":false,\"budget\":0,\"genres\":null,\"homepage\":null,\"imdb_id\":null,\"revenue\":0,\"runtime\":null,\"status\":null,\"tagline\":null,\"credits\":null,\"recommendations\":null,\"similar\":null}")));
    }
}