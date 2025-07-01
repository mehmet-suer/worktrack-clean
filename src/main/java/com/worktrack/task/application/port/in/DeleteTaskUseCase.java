package com.worktrack.task.application.port.in;

import com.worktrack.task.domain.TaskId;

public interface DeleteTaskUseCase {
    void delete(TaskId taskId);
}
