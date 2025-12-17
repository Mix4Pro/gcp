package gcp.demo.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import javax.lang.model.type.ErrorType;

@Builder
public record ErrorDto (
    int code,
    HttpStatus status,
    ErrorType errorType,
    String message
) {
}
