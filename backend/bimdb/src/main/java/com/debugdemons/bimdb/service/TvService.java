package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.domain.TvShowDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TvService {

    @Autowired
    private MovieDBApiConfig movieDBApiConfig;

    @Autowired
    private RestTemplate restTemplate;

    public DiscoverTv getTv(Integer page) {
        String url = movieDBApiConfig.getBaseUrl() + "discover/tv";
        if(page != null) {
            url += "?page=" + page + "&";
        } else {
            url += "?";
        }
        url += "api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, DiscoverTv.class);
    }

    public TvShowDetails getTvShowById(Long id) {
        String url = movieDBApiConfig.getBaseUrl() + "tv/" + id + "?api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, TvShowDetails.class);
    }
}
