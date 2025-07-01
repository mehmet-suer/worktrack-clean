package com.worktrack.task.application.port.out;

import com.worktrack.project.domain.ProjectId;
import com.worktrack.task.domain.Task;

import java.util.List;

public interface TaskQueryPort {
    List<Task> getByProject(ProjectId projectId);
}
