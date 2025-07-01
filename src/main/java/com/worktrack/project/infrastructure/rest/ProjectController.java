package com.worktrack.project.infrastructure.rest;

import com.worktrack.project.domain.Project;
import com.worktrack.project.domain.ProjectId;
import com.worktrack.project.application.port.out.ProjectQueryPort;
import com.worktrack.project.application.port.service.CreateProjectService;
import com.worktrack.project.application.port.service.DeleteProjectService;
import com.worktrack.project.infrastructure.persistence.mapper.ProjectResponseMapper;
import com.worktrack.project.infrastructure.rest.dto.request.CreateProjectRequest;
import com.worktrack.project.infrastructure.rest.dto.request.mapper.CreateProjectRequestMapper;
import com.worktrack.project.infrastructure.rest.dto.response.ProjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final CreateProjectService createProjectService;
    private final ProjectQueryPort projectQueryPort;
    private final ProjectResponseMapper projectResponseMapper;
    private final DeleteProjectService deleteProjectService;
    public ProjectController(CreateProjectService projectService,
                             DeleteProjectService deleteProjectService,
                             ProjectQueryPort projectQueryPort,
                             ProjectResponseMapper projectResponseMapper) {
        this.createProjectService = projectService;
        this.projectQueryPort = projectQueryPort;
        this.projectResponseMapper = projectResponseMapper;
        this.deleteProjectService = deleteProjectService;
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Validated CreateProjectRequest request) {
        Project project = createProjectService.createProject(CreateProjectRequestMapper.toDomain(request));
        return ResponseEntity
                .status(201)
                .body(projectResponseMapper.toDto(project));
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostAuthorize("returnObject == null || returnObject.ownerUsername == authentication.name")
    @GetMapping("/{value}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable("value") Long id) {
        Project project = projectQueryPort.getProjectByIdForced(new ProjectId(id));
        return ResponseEntity.ok(projectResponseMapper.toDto(project));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable("projectId") Long projectId) {
        deleteProjectService.delete(new ProjectId(projectId));
        return ResponseEntity
                .noContent()
                .build();
    }
}
