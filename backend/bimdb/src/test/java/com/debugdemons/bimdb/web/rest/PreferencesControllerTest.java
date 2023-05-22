package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.Preferences;
import com.debugdemons.bimdb.service.PreferencesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PreferencesController.class)
class PreferencesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreferencesService preferencesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserPreferences() throws Exception {
        String username = "john";

        Preferences preferences = new Preferences();
        preferences.setUsername(username);
        // Set up the behavior of the preferencesService mock
        when(preferencesService.getPreferences(username)).thenReturn(preferences);

        mockMvc.perform(get("/api/user/preferences/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username));

        verify(preferencesService, times(1)).getPreferences(username);
        verifyNoMoreInteractions(preferencesService);
    }
}
