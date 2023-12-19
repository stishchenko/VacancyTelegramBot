package com.tish.service;

import com.tish.dto.VacancyDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VacancyService {

	private final Map<String, VacancyDto> vacancies = new HashMap<>();

	@PostConstruct
	public void init() {
		VacancyDto juniorMaDeveloper = new VacancyDto("1", "Junior Dev at MA", "Java Core is required");
		vacancies.put("1", juniorMaDeveloper);
		VacancyDto googleDev = new VacancyDto("2", "Junior Dev at Google", "Welcome to Google!");
		vacancies.put("2", googleDev);
		VacancyDto middle = new VacancyDto("3", "Middle Java dev", "Join our awesome company!");
		vacancies.put("3", middle);
	}

	public List<VacancyDto> getJuniorVacancies() {
		return vacancies.values().stream()
				.filter(v -> v.getTitle().toLowerCase().contains("junior")).toList();
	}

	public VacancyDto get(String id) {
		return vacancies.get(id);
	}
}
