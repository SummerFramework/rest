package org.summerframework.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.summerframework.core.service.MessageI18nService;

/**
 * Created by renan on 27/02/16.
 */
@RestController
public class BaseController {

    @Autowired
    private MessageI18nService messageI18nService;

//    @ExceptionHandler({Exception.class})
//    public ErrorVO exceptionHandler( HttpServletResponse response, Exception exception) {
//
//        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        List<ErrorMessageVO> messages = new ArrayList<>();
//
//        String details = messageI18nService.get("form.validation.error");
//
//        Object target = null;
//
//        if ( exception instanceof MethodArgumentNotValidException){
//            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
//            MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
//            target = e.getBindingResult().getTarget();
//            final List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
//            for (ObjectError err : allErrors) {
//                FieldError error = (FieldError) err;
//                messages.add(new ErrorMessageVO(error.getField(), error.getDefaultMessage()));
//            }
//        }else if ( exception instanceof HttpMessageConversionException){
//            response.setStatus(HttpStatus.BAD_REQUEST.value());
//            HttpMessageConversionException e = (HttpMessageConversionException) exception;
//            details = e.getLocalizedMessage();
//        }else if ( exception instanceof DataIntegrityViolationException){
//            DataIntegrityViolationException e = (DataIntegrityViolationException) exception;
//            final SQLException sqlException = ((ConstraintViolationException) e.getCause()).getSQLException();
//            details = sqlException.getLocalizedMessage();
//        }else if ( exception instanceof EmptyResultDataAccessException){
//            response.setStatus(HttpStatus.NOT_FOUND.value());
//            EmptyResultDataAccessException e = (EmptyResultDataAccessException) exception;
//            details = exception.getLocalizedMessage();
//        }else{
//            details = exception.getLocalizedMessage();
//        }
//
//        return new ErrorVO(response.getStatus(), details, target,  messages);
//    }

    public MessageI18nService getMessageI18nService() {
        return messageI18nService;
    }
}
