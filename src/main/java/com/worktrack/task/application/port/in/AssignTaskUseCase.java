package com.worktrack.task.application.port.in;

import com.worktrack.task.domain.TaskId;
import com.worktrack.user.domain.UserId;

public interface AssignTaskUseCase {
    void assign(TaskId taskId, UserId userId);
}
