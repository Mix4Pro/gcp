package gcp.demo.handler;

import gcp.demo.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException (IllegalArgumentException e) {
        log.error("IllegalArgumentException error {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorDto.builder()
                .code(400)
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build()
        );
    }
}
