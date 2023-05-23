package br.com.wferreiracosta.amy.controllers.impl;

import br.com.wferreiracosta.amy.controllers.ExceptionHandlerController;
import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.StandardError;
import br.com.wferreiracosta.amy.models.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerImpl implements ExceptionHandlerController {

    @Override
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(NOT_FOUND).body(err);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        final var err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            log.error(format("Erro: Campo %s, Mensagem %s", x.getField(), x.getDefaultMessage()));
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
