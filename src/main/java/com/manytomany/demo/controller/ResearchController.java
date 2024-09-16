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
import com.manytomany.demo.service.ResearchersJpaService;

@RestController
public class ResearchController {
   @Autowired
   private ResearchersJpaService researchersJpaService;
   
   @GetMapping("/researchers")
   public ArrayList<Researcher> getResearchers(){
	   return researchersJpaService.getResearchers();
   }
   @GetMapping("/researchers/{researchId}")
   public Researcher  getResearchById(@PathVariable("researcherId") int researcherId) {
	   return researchersJpaService.getResearchById(researcherId);
   }
   @PostMapping("/researchers")
   public Researcher addResearch(@RequestBody Researcher researcher) {
	   return researchersJpaService.addResearch(researcher);
   }
   @PutMapping("/researchers/{researchId}")
   public Researcher updateResearch(@PathVariable("researcherId") int researcherId,@RequestBody Researcher researcher) {
	   return researchersJpaService.updateResearch(researcherId, researcher);
   }
   @DeleteMapping("/researchers/{researchId}")
   public void deleteById(@PathVariable("researcherId") int researcherId) {
	   researchersJpaService.deleteById(researcherId);
   }
   @GetMapping("/researchers/{researchId}/projects")
   public List<Project> getProjectResearcher(@PathVariable("researcherId") int researcherId ){
	   return researchersJpaService.getProjectResearcher(researcherId);
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
