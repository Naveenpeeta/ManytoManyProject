package com.manytomany.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.manytomany.demo.model.Project;

public interface ProjectJpaRepository extends JpaRepository<Project, Integer>  {
    
}
