package com.worktrack.project.infrastructure.persistence;


import com.worktrack.project.infrastructure.persistence.entity.ProjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectSpringDataJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {

}
