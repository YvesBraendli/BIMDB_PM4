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

    @GetMapping
    public Preferences getUserPreferences() {
        //TODO: get username from header
        return preferencesService.getPreferences("username");
    }

    @PutMapping
    public Preferences saveUserPreferences(@RequestBody Preferences preferences) {
        ///TODO: get username from header
        return preferencesService.updatePreferences("username", preferences);
    }
}
