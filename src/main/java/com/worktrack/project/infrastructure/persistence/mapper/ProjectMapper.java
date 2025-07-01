package com.worktrack.project.infrastructure.persistence.mapper;

import com.worktrack.project.domain.Project;
import com.worktrack.project.domain.ProjectId;
import com.worktrack.project.infrastructure.persistence.entity.ProjectJpaEntity;
import com.worktrack.user.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project fromJpaEntity(ProjectJpaEntity entity) {
        if (entity == null) return null;

        return new Project(
                entity.getId() != null ? new ProjectId(entity.getId()) : null,
                entity.getName(),
                entity.getDescription(),
                entity.getOwner() != null ? new UserId(entity.getOwner()) : null
        );
    }

    public ProjectJpaEntity toJpaEntity(Project project) {
        if (project == null) return null;

        ProjectJpaEntity entity = new ProjectJpaEntity();

        entity.setName(project.name());
        entity.setDescription(project.description());

        return entity;
    }
}
