package com.worktrack.task.application.service;

import com.worktrack.project.domain.ProjectId;
import com.worktrack.task.domain.TaskId;
import com.worktrack.task.application.port.out.TaskQueryPort;
import com.worktrack.task.application.port.out.TaskRepository;
import com.worktrack.task.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryService implements TaskQueryPort {

    private final TaskRepository taskRepository;

    public TaskQueryService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> getByProject(ProjectId projectId) {
        return taskRepository.getByProjectId(projectId);
    }

    public Optional<Task> findById(TaskId taskId) {
        return taskRepository.findById(taskId);
    }

}
