package exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistExeption.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceAlreadyExistExeption exp, HttpServletRequest req ) {
        ErrorResponse response= new ErrorResponse(
                HttpStatus.CONFLICT.name(),
                exp.getMessage(),
                LocalDateTime.now(),
                req.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException exp, HttpServletRequest req ) {
        ErrorResponse response= new ErrorResponse(
                HttpStatus.BAD_REQUEST.name(),
                exp.getMessage(),
                LocalDateTime.now(),
                req.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation failed");

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.name(),
                message,
                LocalDateTime.now(),
                req.getRequestURI()
        );

        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleException(
            BadCredentialsException ex,
            HttpServletRequest req) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.name(),
                "Invalid email or password",
                LocalDateTime.now(),
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
}
