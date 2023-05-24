package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.security.JwtUtil;
import com.debugdemons.bimdb.service.MovieService;
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
class DiscoverControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService movieService;
	@MockBean
	private TvService tvService;

	@MockBean
	private JwtUtil jwtUtil;

	@Test
	void discoverMovie() throws Exception {
		DiscoverMovie discoverMovie = new DiscoverMovie();
		discoverMovie.setTotalPages(20);
		when(movieService.getMovies(null, null)).thenReturn(discoverMovie);
		when(jwtUtil.getUsernameFromJWT(null)).thenReturn(null);
		this.mockMvc.perform(get("/api/discover/movies")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"page\":0,\"totalPages\":20,\"totalResults\":0,\"results\":null}")));
	}

	@Test
	void discoverTv() throws Exception {
		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		when(tvService.getTv(null, null)).thenReturn(discoverTv);
		when(jwtUtil.getUsernameFromJWT(null)).thenReturn(null);
		this.mockMvc.perform(get("/api/discover/tv")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"page\":0,\"totalPages\":20,\"totalResults\":0,\"results\":null}")));
	}
}