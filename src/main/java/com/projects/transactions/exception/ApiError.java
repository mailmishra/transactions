package com.projects.transactions.exception;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ApiError {

    private final String message;
    private final String errorId;

}
