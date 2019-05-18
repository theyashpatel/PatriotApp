package ypdp.exception.handler;

import ypdp.api.base.Error;
import ypdp.exception.AuthenticationException;
import ypdp.exception.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody Error handleBadRequest(HttpServletRequest req, BadRequestException ex) {
        return new Error(ex.getCode());
    }

    @ExceptionHandler(value = { AuthenticationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody Error handleUnauthorizedRequest(HttpServletRequest req, AuthenticationException ex) {
        return new Error("BAD_CREDENTIALS");
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody Error handleDataIntegrityViolation(HttpServletRequest req, DataIntegrityViolationException ex) {
        return new Error("RECORD_ALREADY_EXIST");
    }
}
