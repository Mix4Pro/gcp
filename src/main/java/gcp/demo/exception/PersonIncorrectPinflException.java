package gcp.demo.exception;

import org.springframework.http.HttpStatus;

public class PersonIncorrectPinflException extends ApplicationException {
    public PersonIncorrectPinflException (String message) {
        super (400, HttpStatus.BAD_REQUEST,"Incorrect Pinfl type" , message);
    }
}
