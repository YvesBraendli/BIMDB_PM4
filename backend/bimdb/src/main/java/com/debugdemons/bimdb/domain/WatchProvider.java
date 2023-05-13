package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchProvider implements Serializable {

	@JsonProperty("providerName")
	@JsonAlias("provider_name")
	private String providerName;

	@JsonProperty("logoPath")
	@JsonAlias("logo_path")
	private String logoPath;

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
}
