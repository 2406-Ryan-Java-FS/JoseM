package ems.employeemanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Global exception handler to handle specific exceptions and return appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles EmployeeNotFoundException and returns a 404 Not Found response.
     *
     * @param ex The EmployeeNotFoundException.
     * @return A ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    /**
     * Handles DuplicateAccountException and returns a 400 Bad Request response.
     *
     * @param ex The DuplicateAccountException.
     * @return A ResponseEntity with a 400 status and the exception message.
     */
    @ExceptionHandler(DuplicateAccountException.class)
    public ResponseEntity<String> handleDuplicateAccountException(DuplicateAccountException ex){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles AccountNotFoundException and returns a 404 Not Found response.
     *
     * @param ex The AccountNotFoundException.
     * @return A ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles UnauthorizedException and returns a 401 Unauthorized response.
     *
     * @param ex The UnauthorizedException.
     * @return A ResponseEntity with a 401 status and the exception message.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
