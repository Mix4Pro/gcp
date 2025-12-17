package gcp.demo.handler;

import gcp.demo.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


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

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String,String>> handleMethodArgumentNotValidException (
        MethodArgumentNotValidException e) {
        HashMap<String,String> errors = new HashMap<>();
        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(),error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errors);
    }
}
