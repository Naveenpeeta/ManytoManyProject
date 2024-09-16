package com.manytomany.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="research")
public class Researcher {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer researcherId;
	@Column(name="researcherName")
	private  String researcherName;
	@Column(name ="specialization")
	private String specialization;
	
	@ManyToMany(mappedBy="researchers")
	@JsonIgnoreProperties("researchers")
	private List<Project> project;

	public Integer getResearcherId() {
		return researcherId;
	}

	public void setResearcherId(Integer researcherId) {
		this.researcherId = researcherId;
	}

	public String getResearcherName() {
		return researcherName;
	}

	public void setResearcherName(String researcherName) {
		this.researcherName = researcherName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}
	
}
