package com.manytomany.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.manytomany.demo.model.Project;
import com.manytomany.demo.model.Researcher;
import com.manytomany.demo.repository.ProjectJpaRepository;
import com.manytomany.demo.repository.ProjectRepository;
import com.manytomany.demo.repository.ResearcherJpaRepository;

@Service
public class ProjectJpaService implements ProjectRepository  {
	
	@Autowired
	ProjectJpaRepository projectJpaRepository;
	@Autowired
	ResearcherJpaRepository researcherJpaRepository;

	@Override
	public ArrayList<Project> getProjects() {
		List<Project> projectsList = projectJpaRepository.findAll();
		ArrayList<Project> projects = new ArrayList<>(projectsList);
		return projects;
	}

	@Override
	public Project getProjectById(int projectId) {
		try {
			Project project = projectJpaRepository.findById(projectId).get();
			return project;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public Project addProject(Project project) {
		List<Integer> researchIds = new ArrayList<>();
		
		for(Researcher researcher:project.getResearchers()) {
			researchIds.add(researcher.getResearcherId());
		}
		List<Researcher> researchers = researcherJpaRepository.findAllById(researchIds);
		project.setResearchers(researchers);
		projectJpaRepository.save(project);
		return project;
	}

	@Override
	public Project updateProject(int projectId, Project project) {
		try {
			Project newProject = projectJpaRepository.findById(projectId).get();
			if(project.getProjectName()!=null) {
				newProject.setProjectName(project.getProjectName());
			}
			if(project.getBudget()!=0) {
				newProject.setBudget(project.getBudget());
			}
			return projectJpaRepository.save(newProject);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public void deleteById(int projectId) {
		try {
		projectJpaRepository.deleteById(projectId);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public List<Researcher> getProjectResearcher(int projectId) {
		try {
			Project newProject = projectJpaRepository.findById(projectId).get();
			return newProject.getResearchers();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
