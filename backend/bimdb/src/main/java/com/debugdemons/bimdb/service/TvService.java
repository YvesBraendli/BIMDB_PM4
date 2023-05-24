package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.*;
import com.debugdemons.bimdb.model.User;
import com.debugdemons.bimdb.repository.FavoritesRepository;
import com.debugdemons.bimdb.repository.UsersRepository;
import com.debugdemons.bimdb.utils.Filter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class TvService extends BaseService {


	public TvService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
		super(movieDBApiConfig, restTemplate);
	}

	public DiscoverTv getTv(Integer page, String username) {
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl(), "discover/tv")
				.withPageNumber(page);
		return restTemplate.getForObject(tmdbUrlBuilder.build(), DiscoverTv.class);
	}

	public TvShowDetails getTvShowById(Long id) {
		String url = movieDBApiConfig.getBaseUrl() + "tv/" + id + "?append_to_response=credits,recommendations,similar";
		return restTemplate.getForObject(url, TvShowDetails.class);
	}

	public WatchProvidersResult getWatchProviders(Long id) {
		String url = movieDBApiConfig.getBaseUrl() + "tv/" + id + "/watch/providers";
		return restTemplate.getForObject(url, WatchProvidersResult.class);
	}

	public TvShowSeasonDetails getTvShowSeasonDetails(Long id, Long seasonNumber) {
		String url = movieDBApiConfig.getBaseUrl() + "tv/" + id + "/season/" + seasonNumber;
		return restTemplate.getForObject(url, TvShowSeasonDetails.class);
	}
}
