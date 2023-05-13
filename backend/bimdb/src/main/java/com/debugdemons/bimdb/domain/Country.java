package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
	@JsonProperty("iso")
	@JsonAlias("iso_3166_1")
	private String iso;

	@JsonProperty("englishName")
	@JsonAlias("english_name")
	private String englishName;

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
}
