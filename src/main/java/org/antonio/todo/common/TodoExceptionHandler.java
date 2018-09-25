package org.antonio.todo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class TodoExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TodoException.class)
    public String handleBaseException(TodoException e){
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){return e.getMessage();}

}
