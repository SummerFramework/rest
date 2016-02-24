package com.rbarbioni.core.base;
import com.rbarbioni.core.data.JpaRepositoryBase;
import com.rbarbioni.core.model.DomainEntity;
import com.rbarbioni.core.model.valueobject.DomainVO;
import com.rbarbioni.core.model.valueobject.ErrorMessageVO;
import com.rbarbioni.core.model.valueobject.ErrorVO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by renan on 20/02/16.
 */

@RestController
public abstract class ControllerBase<E extends DomainEntity, VO extends DomainVO> {

    ServiceBase<E, VO> serviceBase;

    public ControllerBase (JpaRepositoryBase jpaRepositoryBase){
        this.serviceBase = new ServiceBase<>(jpaRepositoryBase);
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<VO> findAll () throws IOException, IllegalAccessException, InstantiationException {
        return this.serviceBase.findAll();
    }

    @RequestMapping(value = "/actives", method = RequestMethod.GET)
    public List<VO> findActives () throws IOException, IllegalAccessException, InstantiationException {
        return this.serviceBase.findActives();
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public VO findUnique ( @PathVariable String uuid ) throws IOException, InstantiationException, IllegalAccessException {
        return this.serviceBase.findUnique(uuid);
    }

    @RequestMapping( method = RequestMethod.POST)
    public VO create ( @Valid @RequestBody VO domainVO) throws IOException, InstantiationException, IllegalAccessException {
        return this.serviceBase.save(domainVO);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public VO update ( @PathVariable String uuid, @Valid @RequestBody VO domainVO ) throws IOException, InstantiationException, IllegalAccessException {
        return this.serviceBase.save(domainVO);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    public void update ( @PathVariable String uuid) throws IOException {
        this.serviceBase.delete(uuid);
    }

    @ExceptionHandler({Exception.class})
    public ErrorVO exceptionHandler( HttpServletResponse response, Exception exception) {

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        List<ErrorMessageVO> messages = new ArrayList<>();
        String details= "Problemas na requisição";
        Object target = null;

        if ( exception instanceof MethodArgumentNotValidException ){
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
