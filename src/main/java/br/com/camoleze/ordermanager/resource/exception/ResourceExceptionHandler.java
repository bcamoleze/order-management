package br.com.camoleze.ordermanager.resource.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.camoleze.ordermanager.exception.NotFoundException;

@ControllerAdvice // ouvinte
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
		
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		

		List<String> errors = new ArrayList<String>();
		
        ex.getBindingResult().getAllErrors().forEach(error -> {
        	errors.add(error.getDefaultMessage());
        });
		
        // mensagem geral 
        String defaultMessage = "Invalid field(s)";
        
		// customizando meu erro...
		ApiErrorList msgError = new ApiErrorList(HttpStatus.BAD_REQUEST.value() , defaultMessage, new Date(), errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msgError);
		
	}
	
}
