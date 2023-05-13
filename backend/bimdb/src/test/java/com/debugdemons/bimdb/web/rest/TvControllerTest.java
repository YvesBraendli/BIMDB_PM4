package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.*;
import com.debugdemons.bimdb.service.TvService;
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
				.andExpect(content().string(containsString("{\"backdropPath\":null,\"genreIds\":null,\"id\":305,\"originalLanguage\":null,\"overview\":null,\"popularity\":0.0,\"posterPath\":null,\"voteAverage\":-1.0,\"voteCount\":0,\"name\":\"Succession\",\"originalName\":null,\"releaseDate\":null,\"mediaType\":\"tv\",\"originCountry\":null,\"episodeRunTime\":null,\"genres\":null,\"homepage\":null,\"inProduction\":false,\"languages\":null,\"lastAirDate\":null,\"networks\":null,\"numberOfEpisodes\":0,\"numberOfSeasons\":0,\"seasons\":null,\"status\":null,\"tagline\":null,\"type\":null,\"credits\":null,\"recommendations\":null,\"similar\":null}")));
	}

	@Test
	void getTvShowSeasonDetails() throws Exception {
		TvShowSeasonDetails tvShowSeasonDetails = new TvShowSeasonDetails();
		tvShowSeasonDetails.setApiId(305);
		tvShowSeasonDetails.setName("Succession");
		when(movieService.getTvShowSeasonDetails(305L, 1L)).thenReturn(tvShowSeasonDetails);
		this.mockMvc.perform(get("/api/tv/305/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"id\":305,\"airDate\":null,\"episodes\":null,\"name\":\"Succession\",\"overview\":null,\"posterPath\":null,\"seasonNumber\":null}")));
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

		when(movieService.getWatchProviders(305L)).thenReturn(result);
		this.mockMvc.perform(get("/api/tv/305/watch-providers"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"watchProviders\":[{\"buy\":[{\"providerName\":\"Google Play Movies\",\"logoPath\":\"/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg\"}],\"country\":\"CH\",\"flatrate\":[{\"providerName\":\"Google Play Movies\",\"logoPath\":\"/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg\"}],\"rent\":[{\"providerName\":\"Google Play Movies\",\"logoPath\":\"/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg\"}]}]}")));
	}
}