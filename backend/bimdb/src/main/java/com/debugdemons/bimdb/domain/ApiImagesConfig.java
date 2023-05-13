package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ApiImagesConfig implements Serializable {
	@JsonProperty("baseUrl")
	@JsonAlias("base_url")
	private String baseUrl;
	@JsonProperty("secureBaseUrl")
	@JsonAlias("secure_base_url")
	private String secureBaseUrl;
	@JsonProperty("backdropSizes")
	@JsonAlias("backdrop_sizes")
	private List<String> backdropSizes;
	@JsonProperty("logoSizes")
	@JsonAlias("logo_sizes")
	private List<String> logoSizes;
	@JsonProperty("posterSizes")
	@JsonAlias("poster_sizes")
	private List<String> posterSizes;
	@JsonProperty("profileSizes")
	@JsonAlias("profile_sizes")
	private List<String> profileSizes;
	@JsonProperty("stillSizes")
	@JsonAlias("still_sizes")
	private List<String> stillSizes;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getSecureBaseUrl() {
		return secureBaseUrl;
	}

	public void setSecureBaseUrl(String secureBaseUrl) {
		this.secureBaseUrl = secureBaseUrl;
	}

	public List<String> getBackdropSizes() {
		return backdropSizes;
	}

	public void setBackdropSizes(List<String> backdropSizes) {
		this.backdropSizes = backdropSizes;
	}

	public List<String> getLogoSizes() {
		return logoSizes;
	}

	public void setLogoSizes(List<String> logoSizes) {
		this.logoSizes = logoSizes;
	}

	public List<String> getPosterSizes() {
		return posterSizes;
	}

	public void setPosterSizes(List<String> posterSizes) {
		this.posterSizes = posterSizes;
	}

	public List<String> getProfileSizes() {
		return profileSizes;
	}

	public void setProfileSizes(List<String> profileSizes) {
		this.profileSizes = profileSizes;
	}

	public List<String> getStillSizes() {
		return stillSizes;
	}

	public void setStillSizes(List<String> stillSizes) {
		this.stillSizes = stillSizes;
	}
}
