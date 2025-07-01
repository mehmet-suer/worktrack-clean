package com.worktrack.task.infrastructure.persistence.mapper;

import com.worktrack.project.infrastructure.persistence.entity.ProjectJpaEntity;
import com.worktrack.task.infrastructure.persistence.entity.TaskJpaEntity;
import com.worktrack.task.infrastructure.persistence.TaskJpaStatus;
import com.worktrack.project.domain.ProjectId;
import com.worktrack.project.infrastructure.persistence.mapper.ProjectMapper;
import com.worktrack.task.domain.Task;
import com.worktrack.task.domain.TaskId;
import com.worktrack.task.domain.TaskStatus;
import com.worktrack.user.domain.UserId;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TaskMapper {
    private final ProjectMapper projectMapper;

    public TaskMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public Task toDomain(TaskJpaEntity taskJpaEntity) {
        Objects.requireNonNull(taskJpaEntity);
        return new Task(
                new TaskId(taskJpaEntity.getId()),
                taskJpaEntity.getTitle(),
                taskJpaEntity.getDescription(),
                TaskStatus.valueOf(taskJpaEntity.getTaskStatus().name()),
                taskJpaEntity.getProject() != null ? new ProjectId(taskJpaEntity.getProject().getId()) : null,
                taskJpaEntity.getAssignedTo() != null ? new UserId(taskJpaEntity.getAssignedTo()) : null
        );
    }

    public TaskJpaEntity toJpaEntity(Task task) {
        Objects.requireNonNull(task);

        TaskJpaEntity entity = new TaskJpaEntity();
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setTaskStatus(TaskJpaStatus.valueOf(task.getTaskStatus().name()));

        if(task.getProjectId() != null){
            ProjectJpaEntity projectEntity = new ProjectJpaEntity();
            projectEntity.setId(task.getProjectId().value());
            entity.setProject(projectEntity);
        }
        if(task.getAssignedTo() != null) {
            entity.setAssignedTo(task.getAssignedTo().value());
        } else {
            entity.setAssignedTo(null);
        }

        return entity;
    }
}
