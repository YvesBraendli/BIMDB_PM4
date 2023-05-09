package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.ApiConfig;
import com.debugdemons.bimdb.domain.Country;
import com.debugdemons.bimdb.domain.Language;
import com.debugdemons.bimdb.service.ConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/config")
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping()
    public ApiConfig getApiConfig() {
        return configService.getApiConfig();
    }

    @GetMapping("countries")
    public Country[] getCountries() {
        return configService.getCountries();
    }

    @GetMapping("languages")
    public Language[] getLanguages() {
        return configService.getLanguages();
    }

}
