package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.*;
import com.debugdemons.bimdb.model.User;
import com.debugdemons.bimdb.repository.FavoritesRepository;
import com.debugdemons.bimdb.repository.UsersRepository;
import com.debugdemons.bimdb.utils.Filter;
import com.debugdemons.bimdb.utils.FilterCalculator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TvService extends BaseService {

	private final UsersRepository usersRepository;

	private final FavoritesRepository favoritesRepository;

	private final FilterCalculator<TvShowDetails> filterCalculator;

	public TvService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate, UsersRepository usersRepository, FavoritesRepository favoritesRepository, FilterCalculator<TvShowDetails> filterCalculator) {
		super(movieDBApiConfig, restTemplate);
		this.usersRepository = usersRepository;
		this.favoritesRepository = favoritesRepository;
		this.filterCalculator = filterCalculator;
	}

	public DiscoverTv getTv(Integer page, String username) {
		User user = usersRepository.findByUsername(username);
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl())
				.withEndpoint("discover/tv")
				.withPage(page);
		if (user != null) {
			if (user.getAdult() != null) {
				tmdbUrlBuilder.withIncludeAdult(user.getAdult());
			}
			if (StringUtils.hasText(user.getPreferredOriginalLanguage())) {
				tmdbUrlBuilder.withOriginalLanguage(user.getPreferredOriginalLanguage());
			}
			Set<Long> favoriteTvShowIds = favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.TV_SHOW.getType());
			if (!CollectionUtils.isEmpty(favoriteTvShowIds)) {
				Filter filter = filterCalculator.getFilter(getTvShowDetails(favoriteTvShowIds));
				applyFilter(user, tmdbUrlBuilder, filter);
			}
		}
		return restTemplate.getForObject(tmdbUrlBuilder.build(), DiscoverTv.class);
	}

	public TvShowDetails getTvShowById(Long id) {
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl())
				.withEndpoint("tv/" + id)
				.withAppendToResponse(List.of("credits", "recommendations", "similar"));
		return restTemplate.getForObject(tmdbUrlBuilder.build(), TvShowDetails.class);
	}

	public WatchProvidersResult getWatchProviders(Long id) {
		return restTemplate.getForObject(new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl()).withEndpoint("tv/" + id + "/watch/providers").build(), WatchProvidersResult.class);
	}

	public TvShowSeasonDetails getTvShowSeasonDetails(Long id, Long seasonNumber) {
		return restTemplate.getForObject(new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl()).withEndpoint("tv/" + id + "/season/" + seasonNumber).build(), TvShowSeasonDetails.class);
	}

	private Set<TvShowDetails> getTvShowDetails(Set<Long> favoriteTvShowIds) {
		Set<TvShowDetails> tvShowDetails = new HashSet<>();
		if (!CollectionUtils.isEmpty(favoriteTvShowIds)) {
			List<Long> sortedTvShowIds = favoriteTvShowIds.stream()
					.sorted()
					.collect(Collectors.toList());
			for (Long tvShowId : sortedTvShowIds) {
				tvShowDetails.add(getTvShowById(tvShowId));
			}
		}
		return tvShowDetails;
	}
}
