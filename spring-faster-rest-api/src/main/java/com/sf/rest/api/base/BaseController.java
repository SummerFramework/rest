package com.sf.rest.api.base;

import com.sf.rest.api.model.valueobject.ErrorMessageVO;
import com.sf.rest.api.model.valueobject.ErrorVO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by renan on 27/02/16.
 */
public class BaseController {

    @ExceptionHandler({Exception.class})
    public ErrorVO exceptionHandler( HttpServletResponse response, Exception exception) {

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        List<ErrorMessageVO> messages = new ArrayList<>();
        String details= "Problemas na requisição";
        Object target = null;

        if ( exception instanceof MethodArgumentNotValidException){
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
            target = e.getBindingResult().getTarget();
            final List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
            for (ObjectError err : allErrors) {
                FieldError error = (FieldError) err;
                messages.add(new ErrorMessageVO(error.getField(), error.getDefaultMessage()));
            }
        }else if ( exception instanceof HttpMessageConversionException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            HttpMessageConversionException e = (HttpMessageConversionException) exception;
            details = e.getLocalizedMessage();
        }else if ( exception instanceof DataIntegrityViolationException){
            DataIntegrityViolationException e = (DataIntegrityViolationException) exception;
            final SQLException sqlException = ((ConstraintViolationException) e.getCause()).getSQLException();
            details = sqlException.getLocalizedMessage();
        }else{
            details = exception.getLocalizedMessage();
        }

        return new ErrorVO(response.getStatus(), details, target,  messages);
    }
}
