package com.carolin.invasiveplants.ExceptionHandler;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
// log.warn is part of SLF4J that logs warning-level message. Generally helps what's happening in application
// It has different levels from trace, debug, info, warn, error in terms of situations.
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

// Note for future Endpoints. In POST/PUT/PATCH endpoints (endpoints with @RequestBody) we need to add @Valid
// Note 2, When we use requestDTO we need to use annotations as @NotBlank and @Email and @NotNull to be able to catch errors in inputs.
// Note 3, When we use @PathVariable or @RequestParam we need to add @Validated on the controller.

// Handles all exceptions globally and converts to Json responses.
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Utility method for status, hardcoded not found conflict etc.
    private String getStatusText(HttpStatus status) {
        return switch (status) {
            case BAD_REQUEST -> "Bad Request";
            case NOT_FOUND -> "Not Found";
            case CONFLICT -> "Conflict";
            case INTERNAL_SERVER_ERROR -> "Internal server error";
            default -> status.name().replace("_", " ");
        };
    }

    //Handle custom ApiException exceptions
    @ExceptionHandler(ApiException.class) //ServletRequest represents HTTP request coming from client
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException exception, HttpServletRequest request) {
        log.warn("Handled API exception {}", exception.getMessage()); //See import note
        ApiErrorResponse response = new ApiErrorResponse(
                exception.getStatus().value(),
                getStatusText(exception.getStatus()),
                exception.getMessage(),
                request.getRequestURI() // URI is the path where the HTTP request that caused the exception.
        );
        return ResponseEntity.status(exception.getStatus()).body(response);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)       //Method... Used when @Valid fails validation. Which goes in controller methods.
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest req) {
        List<String> details = ex.getBindingResult().getFieldErrors() // BindingResult returns validation results when Spring uses @Valid
                .stream()           //FieldErrors takes the name of the fields that failed for example Email or Username
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        log.warn("Validation failed: {}", details);
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad request",
                "Validation failed",
                req.getRequestURI(),
                details
        );
        return ResponseEntity.badRequest().body(response);
    }

    // Handle ConstraintViolationsException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        List<String> details = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();                          // violation.getPropertyPath says which field failed validation

        log.warn("Constraint violation: {}", details);
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad request",
                "Validation error",
                req.getRequestURI(),
                details
        );
        return ResponseEntity.badRequest().body(response);
    }

    // data integrity violations
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        log.error("Database Integrity error: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                "Database integrity violation",
                req.getRequestURI()
        );
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    } //protects database rules eg unique, FK constraint, nulls etc. Status 409

    // handle type mismatches when url parameter or cannot be converted to right type
    // eg /users/123 = Long = valid. users/abc /= Long = throws
    // uuid not fully sure but here is examples: Valid: /species/550e8400-e29b-41d4-a716-446655440000
    // Invalid: /species/not-a-uuid
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        log.warn("Type mismatch for parameter '{}': {}", ex.getName(), ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad request",
                "Invalid parameter type for '" + ex.getName() + "'",
                req.getRequestURI()
        );
    return ResponseEntity.badRequest().body(response);
    }

    // catch unexpected errors (500s)
    // Catches ALL exceptions, must be last since spring does specific to the least specific
    // log.error, log error for more serious faults since no validation is failed.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnexpected(Exception ex, HttpServletRequest req) {
        log.error("Unexpected error", ex); //no getMessage for safety & sensitive information
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server Error",
                "An unexpected error occurred",
                req.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
