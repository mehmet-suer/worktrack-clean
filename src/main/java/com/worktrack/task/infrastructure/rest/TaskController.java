package com.worktrack.task.infrastructure.rest;

import com.worktrack.project.domain.ProjectId;
import com.worktrack.task.application.service.TaskQueryService;
import com.worktrack.task.domain.TaskId;
import com.worktrack.task.infrastructure.rest.dto.response.mapper.TaskResponseMapper;
import com.worktrack.task.application.port.in.AssignTaskUseCase;
import com.worktrack.task.application.port.in.CreateTaskUseCase;
import com.worktrack.task.application.port.in.DeleteTaskUseCase;
import com.worktrack.task.domain.Task;
import com.worktrack.task.infrastructure.rest.dto.request.AssignTaskRequest;
import com.worktrack.task.infrastructure.rest.dto.request.CreateTaskRequest;
import com.worktrack.task.infrastructure.rest.dto.request.CreateTaskRequestMapper;
import com.worktrack.task.infrastructure.rest.dto.response.TaskResponse;
import com.worktrack.user.domain.UserId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clean/api/v1/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final AssignTaskUseCase assignTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final TaskQueryService taskQueryService;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          AssignTaskUseCase assignTaskUseCase,
                          DeleteTaskUseCase deleteTaskUseCase,
                          TaskQueryService taskQueryService) {
        this.createTaskUseCase = createTaskUseCase;
        this.assignTaskUseCase = assignTaskUseCase;
        this.taskQueryService = taskQueryService;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }


    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @PathVariable Long projectId,
            @RequestBody @Valid CreateTaskRequest request) {

        Task task = createTaskUseCase.createTask(new ProjectId(projectId), CreateTaskRequestMapper.toDomain(request, projectId));
        TaskResponse response = TaskResponseMapper.toResponse(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{taskId}/assign")
    public ResponseEntity<TaskResponse> assignTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @Valid @RequestBody AssignTaskRequest request) {
        assignTaskUseCase.assign(new TaskId(taskId), new UserId(request.userId()));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(@PathVariable Long projectId) {
        List<Task> tasks = taskQueryService.getByProject(new ProjectId(projectId));
        List<TaskResponse> responses = tasks.stream()
                .map(TaskResponseMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        deleteTaskUseCase.delete(new TaskId(taskId));
        return ResponseEntity.noContent().build();
    }
}