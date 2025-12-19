package gcp.demo.exception;

import org.springframework.http.HttpStatus;

public class PersonNotFoundException extends ApplicationException {
    public PersonNotFoundException(String message ) {
        super(404, HttpStatus.NOT_FOUND, "PersonNotFoundException" , message);
    }
}
