package com.manytomany.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manytomany.demo.model.Project;
import com.manytomany.demo.model.Researcher;
import com.manytomany.demo.service.ProjectJpaService;

@RestController
public class ProjectController {
   @Autowired
   private ProjectJpaService projectJpaService;
   
   @GetMapping("/researchers/projects")
   public ArrayList<Project> getProjects(){
	   return projectJpaService.getProjects();
   }
   @GetMapping("/researchers/projects/{projectId}")
   public Project getProjectById(@PathVariable("projectId") int projectId) {
	   return projectJpaService.getProjectById(projectId);
   }
   @PostMapping("/researchers/projects")
   public Project addProject(@RequestBody Project project) {
	   return projectJpaService.addProject(project);
   }
   @PutMapping("/researchers/projects/{projectId}")
   public Project updateProject(@PathVariable("projectId") int projectId,@RequestBody Project project) {
	   return projectJpaService.updateProject(projectId, project);
   }
   @DeleteMapping("/researchers/projects/{projectId}")
   public void deleteById(@PathVariable("projectId") int projectId) {
	   projectJpaService.deleteById(projectId);
   }
   @GetMapping("/projects/{projectId}/researchers")
   public List<Researcher> getProjectResearchers(@PathVariable("projectId") int projectId){
	   return projectJpaService.getProjectResearcher(projectId);
   }
   
}
