package com.worktrack.task.application.port.in;

import com.worktrack.project.domain.ProjectId;
import com.worktrack.task.domain.Task;

public interface CreateTaskUseCase {
  Task createTask(ProjectId projectId, Task domain);
 }
