package br.com.wferreiracosta.amy.controllers;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.StandardError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

public interface ExceptionHandlerController {

    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request);

    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request);

}
