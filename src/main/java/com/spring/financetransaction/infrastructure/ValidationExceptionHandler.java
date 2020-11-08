package com.spring.financetransaction.infrastructure;

import com.spring.financetransaction.domain.exception.NotFoundedException;
import com.spring.financetransaction.domain.exception.ValidationException;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class ValidationExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public ErrorDTO onError(ValidationException exception) {
    ErrorDTO errorDTO =
        exception.getErrors().entrySet().stream()
            .findFirst()
            .map(this::newErrorResponseDTO)
            .orElse(newDefaultErrorResponseDTO());

    log.error(errorDTO.getDetails());

    return errorDTO;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundedException.class)
  public ErrorDTO onBusinessError(NotFoundedException businessException) {
    log.error(businessException.getMessage());

    return ErrorDTO.builder()
        .code(businessException.getCode())
        .details(businessException.getDetails())
        .build();
  }

  private ErrorDTO newDefaultErrorResponseDTO() {
    return ErrorDTO.builder().details("Unknown validation error").build();
  }

  private ErrorDTO newErrorResponseDTO(Entry<String, String> entry) {
    return ErrorDTO.builder().code(entry.getKey()).details(entry.getValue()).build();
  }
}
