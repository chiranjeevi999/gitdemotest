package com.cg.RestfulService.exception;

import java.util.Date;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice // @ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ UserNotFoundException.class }) // just like try-catch
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest rq) {
		System.out.println("Inside handleUserNotFoundException()");

		// ex.getMessage() coming from user defined constructor message
		ExceptionResponse expResp = new ExceptionResponse(ex.getMessage(),
				"The user which you are trying to fetch is not in the system", new Date());
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND);
	}
	
	/** @ExceptionHandler({ UserNotFoundException.class }) // just like try-catch
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public final ExceptionResponse handleUserNotFoundException(UserNotFoundException ex, WebRequest rq) {
		System.out.println("Inside handleUserNotFoundException()");

		// ex.getMessage() coming from user defined constructor message
		ExceptionResponse expResp = new ExceptionResponse(ex.getMessage(),
				"The user which you are trying to fetch is not in the system", new Date());
		return expResp; // 404
	} **/
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
		System.out.println("Inside handleAllExceptions()");
        ExceptionResponse expResp = new ExceptionResponse(ex.getMessage(),"Detail Description of the Exception", new Date());
        return new ResponseEntity<Object>(expResp,HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		System.out.println("Inside handleTypeMismatch()");
		ExceptionResponse expRes = new ExceptionResponse("Invalid Argument", ex.getMessage(), new Date());
		return new ResponseEntity<Object>(expRes, HttpStatus.BAD_REQUEST); // 400
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Inside handleMethodArgumentNotValid()");
		ExceptionResponse expRes = new ExceptionResponse("Argument not valid", ex.getBindingResult().toString(), new Date());
		return new ResponseEntity<Object>(expRes, HttpStatus.BAD_REQUEST); // 400
	}
	
}
