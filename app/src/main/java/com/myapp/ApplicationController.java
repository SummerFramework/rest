package com.myapp;

import com.myapp.model.Application;
import com.myapp.model.valueobject.ApplicationVO;
import com.myapp.repository.ApplicationRepository;
import org.summerframework.rest.PageableController;
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
