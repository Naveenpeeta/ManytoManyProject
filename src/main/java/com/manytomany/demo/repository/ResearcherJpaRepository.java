package com.manytomany.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manytomany.demo.model.Researcher;

public interface ResearcherJpaRepository extends JpaRepository<Researcher, Integer>{

}
