package com.github.summerframework.sample.rest;

import com.github.summerframework.rest.PageableController;
import com.github.summerframework.sample.model.Application;
import com.github.summerframework.sample.model.valueobject.ApplicationVO;
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
