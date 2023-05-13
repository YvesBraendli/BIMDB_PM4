package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Crew extends Credit {

	@JsonProperty("job")
	private String job;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
}
