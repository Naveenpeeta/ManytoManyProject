package com.manytomany.demo.model;

import java.util.List;

//import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
//import javax.persistence.*
@Entity
@Table(name="project")
public class Project {
   
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer projectId;
	  @Column(name="projectName")
	  private String projectName;
	  @Column(name="budget")
	  private double budget;
	  @ManyToMany
	    @JoinTable(name = "researcher_project", 
	    joinColumns = @JoinColumn(name = "projectId"),
	     inverseJoinColumns = @JoinColumn(name = "researcherId"))
	    @JsonIgnoreProperties("projects")
	    private List<Researcher> researchers;
	
	  public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public List<Researcher> getResearchers() {
		return researchers;
	}

	public void setResearchers(List<Researcher> researchers) {
		this.researchers = researchers;
	}

	
	  
	  
	 
	  
}
