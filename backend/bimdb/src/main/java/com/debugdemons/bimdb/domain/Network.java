package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Network implements Serializable {

	@JsonProperty("id")
	private long apiId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("originCountry")
	@JsonAlias("origin_country")
	private String originCountry;
	@JsonProperty("logoPath")
	@JsonAlias("logo_path")
	private String logoPath;

	public long getApiId() {
		return apiId;
	}

	public void setApiId(long apiId) {
		this.apiId = apiId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
}
