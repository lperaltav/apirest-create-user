package cl.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleBadRequest(final NotFoundException e, final WebRequest request) {
        log.error("[NotFoundException] : {}", e);
        return handleExceptionInternal(e, new ErrorResponse(e.getMessage()),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleBadRequest(final ConflictException e, final WebRequest request) {
        log.error("[ConflictException] : {}", e);
        return handleExceptionInternal(e, new ErrorResponse(e.getMessage()),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleBadRequest(final RuntimeException e, final WebRequest request) {
        log.error("[RuntimeException] : {}", e);
        return handleExceptionInternal(e, new ErrorResponse(e.getMessage()),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Data
    @AllArgsConstructor
    private class ErrorResponse {
        private String mensaje;
    }
}
