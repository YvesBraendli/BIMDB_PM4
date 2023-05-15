package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.domain.TvShowDetails;
import com.debugdemons.bimdb.domain.TvShowSeasonDetails;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TvService extends BaseService {

	public TvService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
		super(movieDBApiConfig, restTemplate);
	}

	public DiscoverTv getTv(Integer page) {
		String url = movieDBApiConfig.getBaseUrl() + "discover/tv";
		if (page != null) {
			url += "?page=" + page;
		}
		return restTemplate.getForObject(url, DiscoverTv.class);
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
