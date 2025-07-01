package com.worktrack.user.application.port.out;

import com.worktrack.user.domain.User;
import com.worktrack.user.domain.UserId;

import java.util.Optional;

public interface UserServicePort {
    Optional<User> getUserById(UserId userId);

    Optional<User> getAuthenticatedUserInfo(String token);
}