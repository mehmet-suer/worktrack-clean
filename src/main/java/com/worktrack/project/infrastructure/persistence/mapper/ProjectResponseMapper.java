package com.worktrack.project.infrastructure.persistence.mapper;

import com.worktrack.project.domain.Project;
import com.worktrack.project.infrastructure.rest.dto.response.ProjectResponse;
import com.worktrack.user.infrastructure.rest.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ProjectResponseMapper {
    public ProjectResponse toDto(Project project, UserDto owner) {
        return new ProjectResponse(
                project.id().value(),
                project.name(),
                project.description(),
                owner
        );
    }

    public ProjectResponse toDto(Project project) {
        return toDto(project, null);
    }
}
