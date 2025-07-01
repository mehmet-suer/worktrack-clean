package com.worktrack.task.application.port.out;
import com.worktrack.project.domain.ProjectId;
import com.worktrack.task.domain.TaskId;
import com.worktrack.task.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(TaskId id);
    Task findByIdForced(TaskId id);
    void save(Task task);
    List<Task> getByProjectId(ProjectId id);
    void delete(TaskId taskId);

}