package com.worktrack.common.infrastructure.jwt;

import com.worktrack.user.application.port.out.UserServicePort;
import com.worktrack.user.domain.User;
import com.worktrack.user.infrastructure.mapper.AuthenticatedUserMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserServicePort userService;
    public JwtAuthenticationFilter(UserServicePort userService) {
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        final Optional<User> userOptional = userService.getAuthenticatedUserInfo(token);

        if (userOptional.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userOptional.get();
            var authToken = new UsernamePasswordAuthenticationToken(
                    AuthenticatedUserMapper.fromDomain(user),
                    null,
                    getAuthorities(user)
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);

    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return List.of(() -> "ROLE_" + user.role().name());
    }
}
