package com.tish.dto;

import com.opencsv.bean.CsvBindByName;

public class VacancyDto {
	@CsvBindByName(column = "Id")
	private String id;
	@CsvBindByName(column = "Title")
	private String title;
	@CsvBindByName(column = "Short description")
	private String shortDescription;

	@CsvBindByName(column = "Long description")
	private String longDescription;

	@CsvBindByName(column = "Company")
	private String company;
	@CsvBindByName(column = "Salary")
	private String salary;
	@CsvBindByName(column = "Link")
	private String link;

	public VacancyDto() {
	}

	public VacancyDto(String id, String title, String shortDescription, String longDescription, String company, String salary, String link) {
		this.id = id;
		this.title = title;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.company = company;
		this.salary = salary;
		this.link = link;
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

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
