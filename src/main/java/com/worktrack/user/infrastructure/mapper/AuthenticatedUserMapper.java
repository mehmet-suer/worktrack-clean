package com.worktrack.user.infrastructure.mapper;

import com.worktrack.user.domain.AuthenticatedUser;
import com.worktrack.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserMapper {

    public static AuthenticatedUser fromDomain(User user) {
        return new AuthenticatedUser(
                user.id().value(),
                user.username(),
                user.email()
        );
    }
}
