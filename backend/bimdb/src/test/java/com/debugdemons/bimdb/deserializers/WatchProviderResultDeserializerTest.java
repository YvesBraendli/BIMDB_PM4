package com.debugdemons.bimdb.deserializers;

import com.debugdemons.bimdb.domain.WatchProvider;
import com.debugdemons.bimdb.domain.WatchProviders;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WatchProviderResultDeserializerTest {

    private static final String JSON = """
            {
              "id": 594767,
              "results": {
                "AE": {
                  "link": "https://www.themoviedb.org/movie/594767-shazam-fury-of-the-gods/watch?locale=AE",
                  "buy": [
                    {
                      "logo_path": "/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg",
                      "provider_id": 3,
                      "provider_name": "Google Play Movies",
                      "display_priority": 3
                    }
                  ],
                  "rent": [
                    {
                      "logo_path": "/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg",
                      "provider_id": 3,
                      "provider_name": "Google Play Movies",
                      "display_priority": 3
                    }
                  ]
                },
                "AR": {
                  "link": "https://www.themoviedb.org/movie/594767-shazam-fury-of-the-gods/watch?locale=AR",
                  "rent": [
                    {
                      "logo_path": "/peURlLlr8jggOwK53fJ5wdQl05y.jpg",
                      "provider_id": 2,
                      "provider_name": "Apple TV",
                      "display_priority": 3
                    },
                    {
                      "logo_path": "/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg",
                      "provider_id": 3,
                      "provider_name": "Google Play Movies",
                      "display_priority": 6
                    },
                    {
                      "logo_path": "/cDzkhgvozSr4GW2aRdV22uDuFpw.jpg",
                      "provider_id": 339,
                      "provider_name": "Movistar Play",
                      "display_priority": 9
                    }
                  ],
                  "flatrate": [
                    {
                      "logo_path": "/cDzkhgvozSr4GW2aRdV22uDuFpw.jpg",
                      "provider_id": 339,
                      "provider_name": "Movistar Play",
                      "display_priority": 9
                    }
                  ],
                  "buy": [
                    {
                      "logo_path": "/peURlLlr8jggOwK53fJ5wdQl05y.jpg",
                      "provider_id": 2,
                      "provider_name": "Apple TV",
                      "display_priority": 3
                    },
                    {
                      "logo_path": "/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg",
                      "provider_id": 3,
                      "provider_name": "Google Play Movies",
                      "display_priority": 6
                    }
                  ]
                }
              }
            }
            """;

    private static final String INVALID_JSON = """
            {
              "results": {
                "valid": false
              }
            }
            """;

    @Test
    void testDeserialize() throws JsonProcessingException {
        WatchProvidersResult watchProvidersResult = new ObjectMapper().readValue(JSON, WatchProvidersResult.class);
        assertNotNull(watchProvidersResult);

        List<WatchProviders> watchProvidersList = watchProvidersResult.getWatchProviders();
        assertNotNull(watchProvidersList);
        assertEquals(2, watchProvidersList.size());

        WatchProviders watchProviders;
        watchProviders = watchProvidersList.get(0);
        assertEquals("AE", watchProviders.getCountry());
        assertTrue(watchProviders.getFlatrate().isEmpty());

        assertEquals(1, watchProviders.getRent().size());
        assertGooglePlayMovies(watchProviders.getRent().get(0));

        assertEquals(1, watchProviders.getBuy().size());
        assertGooglePlayMovies(watchProviders.getBuy().get(0));

        watchProviders = watchProvidersList.get(1);
        assertEquals("AR", watchProviders.getCountry());
        assertEquals(1, watchProviders.getFlatrate().size());
        assertEquals(3, watchProviders.getRent().size());
        assertEquals(2, watchProviders.getBuy().size());
    }

    @Test
    void testDeserializeInvalidWatchProviderJson() {
        ObjectMapper mapper = new ObjectMapper();
        assertThrows(RuntimeException.class, () -> mapper.readValue(INVALID_JSON, WatchProvidersResult.class));
    }

    private void assertGooglePlayMovies(WatchProvider watchProvider) {
        assertEquals("Google Play Movies", watchProvider.getProviderName());
        assertEquals("/tbEdFQDwx5LEVr8WpSeXQSIirVq.jpg", watchProvider.getLogoPath());
    }
}
