package com.worktrack.project.infrastructure.rest.dto.request.mapper;

import com.worktrack.project.domain.Project;
import com.worktrack.project.infrastructure.rest.dto.request.CreateProjectRequest;
import com.worktrack.user.domain.UserId;


public class CreateProjectRequestMapper {

    public static Project toDomain(CreateProjectRequest request) {
        return new Project(
                null,
                request.name(),
                request.description(),
                request.ownerId() != null ? new UserId(request.ownerId()) : null
        );
    }

}