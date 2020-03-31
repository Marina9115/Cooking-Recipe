package cookingrecipe.cookingrecipe.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import cookingrecipe.cookingrecipe.exception.EntityDoesNotExistException;
import cookingrecipe.cookingrecipe.exception.EntityExistsException;
import cookingrecipe.cookingrecipe.exception.InvalidEntityException;
import cookingrecipe.cookingrecipe.exception.InvalidRequestException;
import cookingrecipe.cookingrecipe.controller.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
 @RestController 
 @ControllerAdvice 
 public class ExceptionController { 
	Logger log = LoggerFactory.getLogger(ExceptionController.class);
 
 
 	@ExceptionHandler 
 	public ResponseEntity<ErrorResponse> handleEntityDoesNotExistException(EntityDoesNotExistException ex) { 
 		log.error(ex.getMessage(), ex); 
 		return ResponseEntity.status(HttpStatus.BAD_REQUEST) 
 				.body(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value())); 
 	} 
 	@ExceptionHandler 
 	 	public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) { 
 	 		log.error(ex.getMessage(), ex); 
 	 		return ResponseEntity.status(HttpStatus.BAD_REQUEST) 
 	 				.body(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value())); 
 	 	} 
 	 
 	 
 	 	@ExceptionHandler 
 	 	public ResponseEntity<ErrorResponse> handleEntityExistsException(EntityExistsException ex) { 
 	 		log.error(ex.getMessage(), ex); 
 	 		return ResponseEntity.status(HttpStatus.BAD_REQUEST) 
  				.body(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value())); 
 	 	} 

 	 	@ExceptionHandler 
 	  	public ResponseEntity<ErrorResponse> handleInvalidEntityException(InvalidEntityException ex) { 
 	 	 		log.error(ex.getMessage(), ex); 
 	 	 		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE) 
 	 	 				.body(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE.value())); 
 	 	 	} 
 	 	 
 	 	 
 	 	 } 




