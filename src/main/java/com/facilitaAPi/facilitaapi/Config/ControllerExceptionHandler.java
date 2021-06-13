package com.facilitaAPi.facilitaapi.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<?> validationException(ConstraintViolationException exception) {
        List<ValidationErrorMessage> errorMessageList = new ArrayList<>();
        for(ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            errorMessageList.add(new ValidationErrorMessage(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
        }

        return new ResponseEntity<>(errorMessageList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> customValidationException(MethodArgumentNotValidException exception) {
        List<ValidationErrorMessage> exceptionList = new ArrayList<>();
        for(ObjectError error : exception.getAllErrors()) {
            exceptionList.add(new ValidationErrorMessage(error.getObjectName().toString(), error.getDefaultMessage()));
        }
        return new ResponseEntity<>(exceptionList, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {CustomValidationExpection.class})
    public ResponseEntity<?> customValidationException(CustomValidationExpection exception) {
        return new ResponseEntity<>(new ValidationErrorMessage(exception.getEntity(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
