package com.worktrack.project.infrastructure.persistence;

import com.worktrack.common.infrastructure.exception.EntityNotFoundException;
import com.worktrack.common.infrastructure.persistence.base.Status;
import com.worktrack.project.domain.Project;
import com.worktrack.project.domain.ProjectId;
import com.worktrack.project.infrastructure.persistence.mapper.ProjectMapper;
import com.worktrack.project.infrastructure.persistence.entity.ProjectJpaEntity;
import com.worktrack.project.application.port.out.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjectJpaRepositoryAdapter implements ProjectRepository {
    private final ProjectMapper projectMapper;
    private final ProjectSpringDataJpaRepository jpaRepository;


    public ProjectJpaRepositoryAdapter(ProjectMapper projectMapper, ProjectSpringDataJpaRepository jpaRepository) {
        this.projectMapper = projectMapper;
        this.jpaRepository = jpaRepository;
    }


    @Override
    public Optional<Project> findById(ProjectId id) {
        return jpaRepository.findById(id.value())
                .map(projectMapper::fromJpaEntity);
    }


    @Override
    public Project save(Project project) {
        ProjectJpaEntity savedProject = jpaRepository.save(projectMapper.toJpaEntity(project));
        return projectMapper.fromJpaEntity(savedProject);
    }

    @Override
    public void delete(ProjectId id) {
        ProjectJpaEntity project = getProjectByIdForced(id);
        project.setStatus(Status.DELETED);
        jpaRepository.save(project);
    }

    private ProjectJpaEntity getProjectByIdForced(ProjectId id) {
        return jpaRepository
                .findById(id.value())
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + id.value() + " not found"));
    }


}