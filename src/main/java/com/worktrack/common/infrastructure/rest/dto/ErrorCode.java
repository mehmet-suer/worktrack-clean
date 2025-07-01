package com.worktrack.common.infrastructure.rest.dto;

public enum ErrorCode {
    AUTHENTICATION_FAILED,
    INVALID_CREDENTIAL,
    ENTITY_NOT_FOUND,
    VALIDATION_ERROR,
    DB_INTEGRITY,
    DB_DUPLICATE_KEY,
    DB_ACQUIRE_LOCK,
    ACCESS_DENIED,
    DB_QUERY_TIMEOUT,
    INTERNAL_ERROR
}
