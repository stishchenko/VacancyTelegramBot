package com.tish.dto;

public class VacancyDto {
	private String id;
	private String title;
	private String shortDescription;

	public VacancyDto() {
	}

	public VacancyDto(String id, String title, String shortDescription) {
		this.id = id;
		this.title = title;
		this.shortDescription = shortDescription;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
