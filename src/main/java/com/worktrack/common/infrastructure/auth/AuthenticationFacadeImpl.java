package com.worktrack.common.infrastructure.auth;

import com.worktrack.common.infrastructure.exception.EntityNotFoundException;
import com.worktrack.user.domain.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade{
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getCurrentUsername() {
        return getAuthentication().getName();
    }

    @Override
    public Long getCurrentUserId() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof AuthenticatedUser authenticatedUser) {
            return authenticatedUser.id();
        }
        throw new EntityNotFoundException("User value bulunamadı");
    }
}
