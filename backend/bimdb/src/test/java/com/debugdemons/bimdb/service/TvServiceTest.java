package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.domain.TvShowDetails;
import com.debugdemons.bimdb.domain.TvShowSeasonDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TvServiceTest {

    @MockBean
    private RestTemplate restTemplate;
    @Autowired
    private TvService tvService;

    @Test
    void discoverTv() {
        DiscoverTv discoverTv = new DiscoverTv();
        discoverTv.setTotalPages(20);
        when(restTemplate.getForObject("https://api.themoviedb.org/3/discover/tv?api_key=api_key", DiscoverTv.class)).thenReturn(discoverTv);
        assertEquals(discoverTv, tvService.getTv(null));
    }

    @Test
    void discoverTvPage() {
        DiscoverTv discoverTv = new DiscoverTv();
        discoverTv.setTotalPages(20);
        when(restTemplate.getForObject("https://api.themoviedb.org/3/discover/tv?page=20&api_key=api_key", DiscoverTv.class)).thenReturn(discoverTv);
        assertEquals(discoverTv, tvService.getTv(20));
    }

    @Test
    void tvShowDetails() {
        TvShowDetails tvShow = new TvShowDetails();
        tvShow.setApiId(305);
        tvShow.setName("Succession");
        when(restTemplate.getForObject("https://api.themoviedb.org/3/tv/305?api_key=api_key", TvShowDetails.class)).thenReturn(tvShow);
        assertEquals(tvShow, tvService.getTvShowById(305L));
    }

    @Test
    void getTvShowSeasonDetails() {
        TvShowSeasonDetails tvShowSeasonDetails = new TvShowSeasonDetails();
        tvShowSeasonDetails.setApiId(923);
        tvShowSeasonDetails.setName("Succession");
        tvShowSeasonDetails.setSeasonNumber(1);
        tvShowSeasonDetails.setOverview("Succession Season 1");
        tvShowSeasonDetails.setAirDate("2020-09-20");
        tvShowSeasonDetails.setEpisodes(new ArrayList<>());
        tvShowSeasonDetails.setPosterPath("");
        when(restTemplate.getForObject("https://api.themoviedb.org/3/tv/305/season/1?api_key=api_key", TvShowSeasonDetails.class)).thenReturn(tvShowSeasonDetails);
        assertEquals(tvShowSeasonDetails, tvService.getTvShowSeasonDetails(305L, 1L));
    }
}
