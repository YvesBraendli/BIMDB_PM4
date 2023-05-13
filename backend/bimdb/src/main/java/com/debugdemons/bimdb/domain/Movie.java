package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie extends Media {

	@JsonProperty("adult")
	private boolean adult;
	@JsonProperty("video")
	private boolean video;

	public Movie() {
		mediaType = MediaType.MOVIE;
	}

	public boolean isAdult() {
		return adult;
	}

	public boolean isVideo() {
		return video;
	}

}
