package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MediaType {
	MOVIE("movie"),
	TV_SHOW("tv"),
	PERSON("person");

	private final String type;

	MediaType(String type) {
		this.type = type;
	}

	@JsonValue
	public String getType() {
		return type;
	}
}
