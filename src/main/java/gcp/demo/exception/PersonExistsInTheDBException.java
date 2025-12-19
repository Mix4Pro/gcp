package gcp.demo.exception;

import org.springframework.http.HttpStatus;

public class PersonExistsInTheDBException extends ApplicationException {
    public PersonExistsInTheDBException (String message) {
        super(400, HttpStatus.BAD_REQUEST,"Person exists in the DB" , message);
    }
}
