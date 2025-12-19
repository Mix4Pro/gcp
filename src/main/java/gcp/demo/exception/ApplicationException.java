package gcp.demo.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApplicationException extends RuntimeException {
    private int code;
    private HttpStatus status;
    private String errorType;
    private String message;

    public ApplicationException(int code , HttpStatus status , String errorType , String message) {
        super(message);
        this.code = code;
        this.status = status;
        this.errorType = errorType;
        this.message = message;
    }
}
