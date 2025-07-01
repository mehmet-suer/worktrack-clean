package com.worktrack.user.infrastructure.adapter;

import com.worktrack.user.domain.User;
import com.worktrack.user.domain.UserId;
import com.worktrack.user.infrastructure.mapper.UserMapper;
import com.worktrack.user.infrastructure.rest.dto.UserDto;
import com.worktrack.user.application.port.out.UserServicePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class UserServiceClient implements UserServicePort {

    private final WebClient webClient;

    public UserServiceClient(@Qualifier("userServiceClientBean") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<User> getUserById(UserId userId) {
        try {
            UserDto user = webClient.get()
                    .uri("layered/api/v1/users/{id}", userId.value())
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            response -> {
                                if (response.statusCode() == HttpStatus.NOT_FOUND) {
                                    return Mono.empty();
                                }

                                return response.createException();
                            }
                    )
                    .bodyToMono(UserDto.class)
                    .block();

            return Optional.ofNullable(user).map(UserMapper::toDomain);
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<User> getAuthenticatedUserInfo(String token) {
        UserDto userInfo = webClient.get()
                .uri("layered/api/v1/auth/me")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> {
                            if (response.statusCode() == HttpStatus.NOT_FOUND) {
                                return Mono.empty();
                            }

                            return response.createException();
                        }
                )
                .bodyToMono(UserDto.class)
                .block();
        return Optional.ofNullable(userInfo).map(UserMapper::toDomain);

    }

}
