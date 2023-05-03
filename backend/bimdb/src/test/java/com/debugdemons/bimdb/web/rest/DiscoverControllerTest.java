package com.debugdemons.bimdb.web.rest;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.service.MovieService;
import com.debugdemons.bimdb.service.TvService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DiscoverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;
    @MockBean
    private TvService tvService;


    @Test
    void discoverMovie() throws Exception {
        DiscoverMovie discoverMovie = new DiscoverMovie();
        discoverMovie.setTotalPages(20);
        when(movieService.getMovies(null)).thenReturn(discoverMovie);
        this.mockMvc.perform(get("/api/discover/movies")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"page\":0,\"total_pages\":20,\"total_results\":0,\"results\":null}")));
    }

    @Test
    void discoverTv() throws Exception {
        DiscoverTv discoverTv = new DiscoverTv();
        discoverTv.setTotalPages(20);
        when(tvService.getTv(null)).thenReturn(discoverTv);
        this.mockMvc.perform(get("/api/discover/tv")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"page\":0,\"total_pages\":20,\"total_results\":0,\"results\":null}")));
    }
}