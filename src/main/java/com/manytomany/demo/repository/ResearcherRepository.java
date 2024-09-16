package com.manytomany.demo.repository;

import java.util.ArrayList;
import java.util.List;
import com.manytomany.demo.model.Project;


import com.manytomany.demo.model.Researcher;

public interface ResearcherRepository {
	  ArrayList<Researcher> getResearchers();
	    
	  Researcher getResearchById(int researcherId);
	    Researcher addResearch(Researcher researcher);
	    Researcher updateResearch(int researcherId,Researcher researcher);
	    void deleteById(int researcherId);
	    
	    List<Project> getProjectResearcher(int researcherId);
}
