package com.worktrack.project.infrastructure.rest.dto.response;

import com.worktrack.user.infrastructure.rest.dto.UserDto;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        UserDto owner
) {
}

