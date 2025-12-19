package gcp.demo.handler;

import gcp.demo.dto.ErrorDto;
import gcp.demo.exception.PersonExistsInTheDBException;
import gcp.demo.exception.PersonIncorrectPinflException;
import gcp.demo.exception.PersonNotFoundException;
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
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorDto> PersonNotFoundException (PersonNotFoundException e) {
        log.error("Person not found error {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorDto.builder()
                .code(404)
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build()
        );
    }

    @ExceptionHandler(PersonIncorrectPinflException.class)
    public ResponseEntity<ErrorDto> handlePersonIncorrectPinflException (PersonIncorrectPinflException e) {
        log.error("Pinfl is incorrect {}",e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorDto.builder()
                .code(400)
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build()
        );
    }

    @ExceptionHandler(PersonExistsInTheDBException.class)
    public ResponseEntity<ErrorDto> handlePersonExistsInTheDBExcpetion (PersonExistsInTheDBException e) {
        log.error("Person exists in the DB exception {}",e.getMessage());

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
