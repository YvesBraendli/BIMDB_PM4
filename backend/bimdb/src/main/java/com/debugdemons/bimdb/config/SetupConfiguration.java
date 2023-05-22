package com.debugdemons.bimdb.config;

import com.debugdemons.bimdb.model.UserPreferences;
import com.debugdemons.bimdb.repository.PreferencesRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("dev")
public class SetupConfiguration {

    private PreferencesRepository preferencesRepository;

    @Autowired
    public SetupConfiguration(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    @PostConstruct
    public void createDemoData() {
        /*
          "genres": [
    {
      "id": 35,
      "name": "Comedy"
    },
    {
      "id": 10749,
      "name": "Romance"
    },
    {
      "id": 18,
      "name": "Drama"
    }
        {
      "id": 12,
      "name": "Adventure"
    },
    {
      "id": 10751,
      "name": "Family"
    },
    {
      "id": 14,
      "name": "Fantasy"
    },
    {

          {
        "adult": false,
        "gender": 2,
        "id": 4566,
        "known_for_department": "Acting",
        "name": "Alan Rickman",
        "original_name": "Alan Rickman",
        "popularity": 13.377,
        "profile_path": "/7tADZs4ILE93oJ5pAh6mKQFEq2m.jpg",
        "cast_id": 33,
        "character": "Harry",
        "credit_id": "52fe424bc3a36847f8013391",
        "order": 0
      },
      {
        "adult": false,
        "gender": 2,
        "id": 2440,
        "known_for_department": "Acting",
        "name": "Bill Nighy",
        "original_name": "Bill Nighy",
        "popularity": 27.877,
        "profile_path": "/ixFI2YCGNGJfwlpI8iyhvVZRg8C.jpg",
        "cast_id": 15,
        "character": "Billy Mack",
        "credit_id": "52fe424bc3a36847f8013351",
        "order": 1
      },
      {
        "adult": false,
        "gender": 2,
        "id": 5472,
        "known_for_department": "Acting",
        "name": "Colin Firth",
        "original_name": "Colin Firth",
        "popularity": 15.954,
        "profile_path": "/z6wxnkqSTnzO1tcBui0ss7ehdm9.jpg",
        "cast_id": 18,
        "character": "Jamie Bennett",
        "credit_id": "52fe424bc3a36847f8013359",
        "order": 2
      },
      {
        "adult": false,
        "gender": 1,
        "id": 7056,
        "known_for_department": "Acting",
        "name": "Emma Thompson",
        "original_name": "Emma Thompson",
        "popularity": 19.492,
        "profile_path": "/zQ0hWHh3tzaOOziPbABQI8s9WCu.jpg",
        "cast_id": 21,
        "character": "Karen",
        "credit_id": "52fe424bc3a36847f8013365",
        "order": 3
      },
      {
        "adult": false,
        "gender": 2,
        "id": 3291,
        "known_for_department": "Acting",
        "name": "Hugh Grant",
        "original_name": "Hugh Grant",
        "popularity": 21.232,
        "profile_path": "/tUHkXYdwm405DjBm2IpPxGjYkjj.jpg",
        "cast_id": 30,
        "character": "The Prime Minister",
        "credit_id": "52fe424bc3a36847f8013385",
        "order": 4
      },
      {
        "adult": false,
        "gender": 1,
        "id": 350,
        "known_for_department": "Acting",
        "name": "Laura Linney",
        "original_name": "Laura Linney",
        "popularity": 21.047,
        "profile_path": "/ztQXGmNLzhDV22rAvcXzCG4d0cy.jpg",
        "cast_id": 36,
        "character": "Sarah",
        "credit_id": "52fe424bc3a36847f801339f",
        "order": 5
      },
      {
        "adult": false,
        "gender": 2,
        "id": 3896,
        "known_for_department": "Acting",
        "name": "Liam Neeson",
        "original_name": "Liam Neeson",
        "popularity": 69.671,
        "profile_path": "/bboldwqSC6tdw2iL6631c98l2Mn.jpg",
        "cast_id": 20,
        "character": "Daniel",
        "credit_id": "52fe424bc3a36847f8013361",
        "order": 6
      },
      {
        "adult": false,
        "gender": 1,
        "id": 47714,
        "known_for_department": "Acting",
        "name": "Martine McCutcheon",
        "original_name": "Martine McCutcheon",
        "popularity": 4.698,
        "profile_path": "/tKn7haEIq0jUJoR4JHKYA660B9h.jpg",
        "cast_id": 35,
        "character": "Natalie",
        "credit_id": "52fe424bc3a36847f801339b",
        "order": 7
      },
      {
        "adult": false,
        "gender": 2,
        "id": 7062,
        "known_for_department": "Acting",
        "name": "Andrew Lincoln",
        "original_name": "Andrew Lincoln",
        "popularity": 17.083,
        "profile_path": "/gR4RzQTDsMfVv8oEh2VRbO8LkFz.jpg",
        "cast_id": 28,
        "character": "Mark",
        "credit_id": "52fe424bc3a36847f801337d",
        "order": 8
      },
      {
        "adult": false,
        "gender": 2,
        "id": 5294,
        "known_for_department": "Acting",
        "name": "Chiwetel Ejiofor",
        "original_name": "Chiwetel Ejiofor",
        "popularity": 25.024,
        "profile_path": "/kq5DDnqqofoRI0t6ddtRlsJnNPT.jpg",
        "cast_id": 27,
        "character": "Peter",
        "credit_id": "52fe424bc3a36847f8013379",
        "order": 9
      },
      {
        "adult": false,
        "gender": 2,
        "id": 7053,
        "known_for_department": "Acting",
        "name": "Gregor Fisher",
        "original_name": "Gregor Fisher",
        "popularity": 5.415,
        "profile_path": "/Dcn4zDxFbU4eFw4i5M6KUepmnu.jpg",
        "cast_id": 16,
        "character": "Joe",
        "credit_id": "52fe424bc3a36847f8013355",
        "order": 10
      },
      {
        "adult": false,
        "gender": 1,
        "id": 7059,
        "known_for_department": "Acting",
        "name": "Heike Makatsch",
        "original_name": "Heike Makatsch",
        "popularity": 11.282,
        "profile_path": "/vcrkljvBhhZx5aoBNEmNwDooNkA.jpg",
        "cast_id": 24,
        "character": "Mia",
        "credit_id": "52fe424bc3a36847f801336d",
        "order": 11
      },
      {
        "adult": false,
        "gender": 1,
        "id": 116,
        "known_for_department": "Acting",
        "name": "Keira Knightley",
        "original_name": "Keira Knightley",
        "popularity": 29.834,
        "profile_path": "/8OpsaapXmwpLQqHvN5iwoIElERY.jpg",
        "cast_id": 29,
        "character": "Juliet",
        "credit_id": "52fe424bc3a36847f8013381",
        "order": 12
      },
      {
        "adult": false,
        "gender": 2,
        "id": 7058,
        "known_for_department": "Acting",
        "name": "Kris Marshall",
        "original_name": "Kris Marshall",
        "popularity": 12.501,
        "profile_path": "/gBq5B3HETUfgdK3vyXXLT7d4oXs.jpg",
        "cast_id": 23,
        "character": "Colin Frissell",
        "credit_id": "52fe424bc3a36847f8013369",
        "order": 13
      },
      {
        "adult": false,
        "gender": 1,
        "id": 118763,
        "known_for_department": "Acting",
        "name": "Lúcia Moniz",
        "original_name": "Lúcia Moniz",
        "popularity": 5.095,
        "profile_path": "/ffrW3ZQhym3wbxvs2XbSozSh8oi.jpg",
        "cast_id": 50,
        "character": "Aurelia",
        "credit_id": "566438c99251412ac4007f90",
        "order": 14
      },
      {
        "adult": false,
        "gender": 2,
        "id": 7060,
        "known_for_department": "Acting",
        "name": "Martin Freeman",
        "original_name": "Martin Freeman",
        "popularity": 21.337,
        "profile_path": "/pLG7mmxBXXTVAgzXvQl0ap3qlJU.jpg",
        "cast_id": 25,
        "character": "John",
        "credit_id": "52fe424bc3a36847f8013371",
        "order": 15
      },
      {
        "adult": false,
        "gender": 2,
        "id": 17289,
        "known_for_department": "Acting",
        "name": "Rodrigo Santoro",
        "original_name": "Rodrigo Santoro",
        "popularity": 10.782,
        "profile_path": "/d3MaF9SPHDn2PMYHuqdnO0Csik6.jpg",
        "cast_id": 38,
        "character": "Karl",
        "credit_id": "52fe424bc3a36847f80133a3",
        "order": 16
      },
      {
        "adult": false,
        "gender": 2,
        "id": 25663,
        "known_for_department": "Acting",
        "name": "Thomas Brodie-Sangster",
        "original_name": "Thomas Brodie-Sangster",
        "popularity": 25.287,
        "profile_path": "/ovfgjgaE7aAXKYaemABX6pJFwRk.jpg",
        "cast_id": 48,
        "character": "Sam",
        "credit_id": "52fe424cc3a36847f80133c9",
        "order": 17
      },
      {
        "adult": false,
        "gender": 2,
        "id": 10730,
        "known_for_department": "Acting",
        "name": "Rowan Atkinson",
        "original_name": "Rowan Atkinson",
        "popularity": 24.466,
        "profile_path": "/wxTgS4SFanVKbnvu9xqOzNJWJwz.jpg",
        "cast_id": 39,
        "character": "Rufus, jewellery salesman",
        "credit_id": "52fe424cc3a36847f80133a7",
        "order": 18
      },

         */
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setUsername("username");
        userPreferences.setReleaseYearTo(2010);
        List<Long> favoriteMovieGenres = new ArrayList<>();
        favoriteMovieGenres.add(10749L);
        userPreferences.setFavoriteMovieGenres(favoriteMovieGenres);
        List<Long> favoriteActors = new ArrayList<>();
        favoriteActors.add(10730L);
        favoriteActors.add(116L);
        userPreferences.setFavoriteActors(favoriteActors);
        preferencesRepository.save(userPreferences);
    }
}
