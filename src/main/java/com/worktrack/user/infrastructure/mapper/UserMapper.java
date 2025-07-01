package com.worktrack.user.infrastructure.mapper;

import com.worktrack.user.domain.Role;
import com.worktrack.user.domain.User;
import com.worktrack.user.domain.UserId;
import com.worktrack.user.infrastructure.rest.dto.UserDto;

public class UserMapper {
    public static User toDomain(UserDto dto) {
        return new User(
                new UserId(dto.id()),
                dto.username(),
                dto.email(),
                dto.fullName(),
                dto.role() != null ? Role.valueOf(dto.role()) : null
        );
    }
}
