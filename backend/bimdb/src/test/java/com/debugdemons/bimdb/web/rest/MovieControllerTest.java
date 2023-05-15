package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.MovieDetails;
import com.debugdemons.bimdb.domain.WatchProvider;
import com.debugdemons.bimdb.domain.WatchProviders;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import com.debugdemons.bimdb.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
				.andExpect(content().string(containsString("{\"backdropPath\":null,\"genreIds\":null,\"id\":538,\"originalLanguage\":null,\"overview\":null,\"popularity\":0.0,\"posterPath\":null,\"voteAverage\":-1.0,\"voteCount\":0,\"name\":\"Interstellar\",\"originalName\":null,\"releaseDate\":null,\"mediaType\":\"movie\",\"adult\":false,\"video\":false,\"budget\":null,\"genres\":null,\"homepage\":null,\"imdbId\":null,\"revenue\":null,\"runtime\":null,\"status\":null,\"tagline\":null,\"credits\":null,\"recommendations\":null,\"similar\":null}")));
	}

	@Test
	void getWatchProviders() throws Exception {
		WatchProvider watchProvider = new WatchProvider();
		watchProvider.setProviderName("Google Play Movies");
		watchProvider.setLogoPath("/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg");

		WatchProviders watchProviders = new WatchProviders();
		watchProviders.setCountry("CH");
		watchProviders.getFlatrate().add(watchProvider);
		watchProviders.getRent().add(watchProvider);
		watchProviders.getBuy().add(watchProvider);

		WatchProvidersResult result = new WatchProvidersResult();
		result.setWatchProviders(List.of(watchProviders));

		when(movieService.getWatchProviders(538L)).thenReturn(result);
		this.mockMvc.perform(get("/api/movie/538/watch-providers"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"watchProviders\":[{\"buy\":[{\"providerName\":\"Google Play Movies\",\"logoPath\":\"/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg\"}],\"country\":\"CH\",\"flatrate\":[{\"providerName\":\"Google Play Movies\",\"logoPath\":\"/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg\"}],\"rent\":[{\"providerName\":\"Google Play Movies\",\"logoPath\":\"/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg\"}]}]}")));
	}
}