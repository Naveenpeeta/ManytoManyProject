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
import com.manytomany.demo.repository.ResearcherJpaRepository;
import com.manytomany.demo.repository.ResearcherRepository;

@Service
public class ResearchersJpaService implements ResearcherRepository {
	
	@Autowired
	private ResearcherJpaRepository researcherJpaRepository;
	@Autowired
	private ProjectJpaRepository projectJpaRepository;

	@Override
	public ArrayList<Researcher> getResearchers() {
		List<Researcher> researchList =researcherJpaRepository.findAll();
		ArrayList<Researcher> research = new ArrayList<>(researchList);
		return  research;
	}

	@Override
	public Researcher getResearchById(int researcherId) {
		try {
			Researcher research = researcherJpaRepository.findById(researcherId).get();
			return research;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public Researcher addResearch(Researcher researcher) {
          List <Integer> projectIds = new ArrayList<>();
          for(Project project:researcher.getProject()) {
        	  projectIds.add(project.getProjectId());
        	  
          }
          List <Project> projects = projectJpaRepository.findAllById(projectIds);
          for (Project project : projects) {
              project.getResearchers().add(researcher);
          }
          Researcher savedResearcher = researcherJpaRepository.save(researcher);
          projectJpaRepository.saveAll(projects);
		return savedResearcher;
	}

	@Override
	public Researcher updateResearch(int researcherId, Researcher researcher) {
           try {
        	   Researcher newResearch = researcherJpaRepository.findById(researcherId).get();
        	   if(researcher.getResearcherName()!=null) {
        		   newResearch.setResearcherName(researcher.getResearcherName());
        	   }
        	   if(researcher.getSpecialization()!=null) {
        		   newResearch.setSpecialization(researcher.getSpecialization());
        	   }
        	   if(researcher.getProject()!=null) {
        		   List<Project> projects = newResearch.getProject();
        		   for(Project project:projects) {
        			   project.getResearchers().remove(newResearch);
        		   }
        		   projectJpaRepository.saveAll(projects);
        		   List<Integer> newProjectIds = new ArrayList<>();
                   for (Project project : researcher.getProject()) {
                       newProjectIds.add(project.getProjectId());
                   }
                   List<Project> newProjects = projectJpaRepository.findAllById(newProjectIds);
                   for (Project project : newProjects) {
                       project.getResearchers().add(newResearch);
                   }
                   projectJpaRepository.saveAll(projects);
                   newResearch.setProject(newProjects);
        	   }
        	   return researcherJpaRepository.save(newResearch);
           }catch(Exception e) {
   			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
   		}
	}

	@Override
	public void deleteById(int researcherId) {
		try {
			Researcher  research = researcherJpaRepository.findById(researcherId).get();
			List<Project> projects = research.getProject();
			for(Project project:projects) {
				project.getResearchers().remove(research);
			}
			projectJpaRepository.saveAll(projects);
			researcherJpaRepository.deleteById(researcherId);
		}catch(Exception e) {
   			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
   		}
		
	}

	@Override
	public List<Project> getProjectResearcher(int researcherId) {
		try {
		Researcher resercher = researcherJpaRepository.findById(researcherId).get();
		    return resercher.getProject();
		}catch(Exception e) {
   			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
   		}
		
	}

}
