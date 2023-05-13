package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TvShow extends Media {

	@JsonProperty("originCountry")
	@JsonAlias("origin_country")
	private List<String> originCountry;

	public TvShow() {
		mediaType = MediaType.TV_SHOW;
	}

	public List<String> getOriginCountry() {
		return originCountry;
	}

}
