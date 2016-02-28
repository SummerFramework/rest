package com.sf.rest.api.app;

import com.sf.rest.api.app.model.Application;
import com.sf.rest.api.app.model.valueobject.ApplicationVO;
import com.sf.rest.api.app.repository.ApplicationRepository;
import com.sf.rest.api.base.PageableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by renan on 20/02/16.
 */
@RestController
@RequestMapping("/application")
public class ApplicationController extends PageableController<Application, ApplicationVO> {

    @Autowired
    public ApplicationController(ApplicationRepository applicationRepository) {
        super(applicationRepository);
    }


}
