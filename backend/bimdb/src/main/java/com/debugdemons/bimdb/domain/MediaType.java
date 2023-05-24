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

	public static MediaType fromString(String type) {
		for (MediaType mediaType : MediaType.values()) {
			if (mediaType.getType().equalsIgnoreCase(type)) {
				return mediaType;
			}
		}
		throw new IllegalArgumentException("Invalid MediaType: " + type);
	}
}
