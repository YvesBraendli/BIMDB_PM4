package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.Preferences;
import com.debugdemons.bimdb.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user/preferences")
public class PreferencesController {

    private final PreferencesService preferencesService;

    @Autowired
    public PreferencesController(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    @GetMapping("/{username}")
    public Preferences getUserPreferences(@PathVariable String username) {
        return preferencesService.getPreferences(username);
    }

    @PutMapping("/{username}")
    public Preferences saveUserPreferences(@PathVariable String username, @RequestBody Preferences preferences) {
        return preferencesService.updatePreferences(username, preferences);
    }
}
