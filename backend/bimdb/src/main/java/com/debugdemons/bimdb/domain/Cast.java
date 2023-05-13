package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cast extends Credit {

	@JsonProperty("character")
	private String character;

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
}
