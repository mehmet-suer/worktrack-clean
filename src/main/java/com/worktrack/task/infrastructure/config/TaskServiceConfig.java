package com.worktrack.task.infrastructure.config;

import com.worktrack.task.application.port.in.AssignTaskUseCase;
import com.worktrack.task.application.port.out.TaskRepository;
import com.worktrack.task.application.service.AssignTaskService;
import com.worktrack.user.application.port.out.UserServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskServiceConfig {
    @Bean
    public AssignTaskUseCase assignTaskUseCase(TaskRepository taskRepository,
                                               UserServicePort userServiceClient) {
        return new AssignTaskService(taskRepository, userServiceClient);
    }
}
