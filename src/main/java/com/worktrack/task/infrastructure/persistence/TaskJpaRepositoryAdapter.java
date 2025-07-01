package com.worktrack.task.infrastructure.persistence;

import com.worktrack.common.infrastructure.exception.EntityNotFoundException;
import com.worktrack.common.infrastructure.persistence.base.Status;
import com.worktrack.project.domain.ProjectId;
import com.worktrack.task.application.port.out.TaskRepository;
import com.worktrack.task.domain.TaskId;
import com.worktrack.task.infrastructure.persistence.mapper.TaskMapper;
import com.worktrack.task.infrastructure.persistence.entity.TaskJpaEntity;
import com.worktrack.task.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskJpaRepositoryAdapter implements TaskRepository {
    private final TaskMapper taskMapper;
    private final TaskSpringDataJpaRepository jpaRepository;

    public TaskJpaRepositoryAdapter(TaskMapper taskMapper, TaskSpringDataJpaRepository jpaRepository) {
        this.taskMapper = taskMapper;
        this.jpaRepository = jpaRepository;
    }


    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.empty();
    }

    @Override
    public Task findByIdForced(TaskId taskId) {
        TaskJpaEntity entity = jpaRepository.findById(taskId.value())
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + taskId.value() + " found"));
        return taskMapper.toDomain(entity);
    }

    @Override
    public void save(Task task) {
        TaskJpaEntity entity = taskMapper.toJpaEntity(task);
        jpaRepository.save(entity);
    }

    @Override
    public List<Task> getByProjectId(ProjectId id) {
        return jpaRepository.listByProjectId(id.value())
                .stream()
                .map(taskMapper::toDomain)
                .toList();
    }


    @Override
    public void delete(TaskId id) {
        TaskJpaEntity project = getTaskByIdForced(id);
        project.setStatus(Status.DELETED);
        jpaRepository.save(project);
    }

    private TaskJpaEntity getTaskByIdForced(TaskId id) {
        return jpaRepository
                .findById(id.value())
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id.value() + " not found"));
    }

}
