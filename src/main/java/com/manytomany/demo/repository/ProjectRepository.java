package com.manytomany.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.manytomany.demo.model.Project;
import com.manytomany.demo.model.Researcher;

public interface ProjectRepository {
     ArrayList<Project> getProjects();
    
    Project getProjectById(int projectId);
    Project addProject(Project project);
    Project updateProject(int projectId,Project project);
    void deleteById(int projectId);
    
    List<Researcher> getProjectResearcher(int projectId);	 
}
