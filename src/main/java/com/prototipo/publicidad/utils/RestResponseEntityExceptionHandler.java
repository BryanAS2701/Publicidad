package com.prototipo.publicidad.utils;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.prototipo.publicidad.utils.dto.ErrorMessage;

import io.micrometer.common.lang.Nullable;
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(OptionalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage OptionalNotFoundException(OptionalNotFoundException exception){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return message;
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                Map<String, Object> errores = new HashMap<>();
                ex.getBindingResult().getFieldErrors().forEach(error ->{
                    errores.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> invalidInputException(InvalidInputException exception) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody 
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder errorMessageBuilder = new StringBuilder(); 
        ex.getConstraintViolations().forEach(violation -> {
            errorMessageBuilder.append("field '")
                    .append(violation.getPropertyPath()).append("': ") 
                    .append(violation.getMessage()).append(". "); 
        });
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, errorMessageBuilder.toString()); 
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ErrorMessage> error2(HttpStatus status, String mensaje) {
    ErrorMessage errorMessage = new ErrorMessage(status, mensaje);
    return new ResponseEntity<>(errorMessage, status);
    }

}
